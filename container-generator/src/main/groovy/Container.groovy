import builders.IndexBuilder
import builders.LetterBuilder
import helper.ClientImporter
import helper.ConfigBuilder
import helper.PdfStore

def configBuilder = new ConfigBuilder()
def config        = configBuilder.getConfig()
def basedir       = new File('.').getAbsolutePath()

def ant = new AntBuilder()
def pdf = new PdfStore()
pdf.initialize(config)

def clientImporter = new ClientImporter(config)
def clients = clientImporter.getClients()

def cli = new CliBuilder(usage: 'groovy container.groovy -[hmscp]')
cli.with {
    h longOpt: 'help', 'Show this usage information'
    m longOpt: 'multiple', 'build multiple containers. One recipient, one attachement one Container but as many times as datasets in the input file.'
    s longOpt: 'single', 'All letters and attachements in one big index-file and container.'
    c longOpt: 'showConfig', 'Show the actual configuration an default settings'
    p longOpt: 'setProperty', args: 2, valueSeparator: '=', argName: 'property=value', 'set value for given property'
}
cli.setFooter('good luck and keep smiling! :-)')

OptionAccessor options = cli.parse(args)

// help message, when no options or the -h option are given
if ( options.h ) {
    println(new File(basedir + '/../../../README').text)
    cli.usage()
    return
}

// show config settings
if (options.c) {
    def out = new StringBuffer()
    out << ' Config Settings '.center(25,'*') << '\n\n'
    configBuilder.showConfig()
    return
}

//set config property
if (options.p) {
    configBuilder.setConfigParam(options.ps)
    return
}

if (options.m) {
    ant.echo('running in multiple mode')
    ant.echo("cleaning up...")
    ant.delete(dir: "${config.directory.target} ")

    for (client in clients) {

        def targetFolder = new File(basedir + config.directory.target + config.containerNamePrefix + client.containerName)

        ant.echo('building target dir...')
        ant.mkdir(dir: targetFolder)

        def index       = new File(targetFolder.absolutePath + "/Index.xml")
        def coverLetter = new File(targetFolder.absolutePath + '/' + 'Anschreiben_' + client.getContainerName() + '.xml')

        def indexText       = new IndexBuilder(config, client, 1)
        def coverLetterText = new LetterBuilder(config, client, 1)

        def completeIndexText = indexText.getHeader() + indexText.getContent() + indexText.getFooter()

        index.write(completeIndexText)
        coverLetter.write(coverLetterText.getContent())

        ant.echo('copy and zip...')
        ant.copy(file: pdf.getRandom(), tofile: "${targetFolder}/Anhang_" + client.getContainerName() + '.pdf')
        ant.zip(destfile: "${targetFolder}.zip", basedir: "${targetFolder}")
        ant.delete(dir: targetFolder)
    }
}

if (options.s) {
    ant.echo('running in multiple mode')
    ant.echo("cleaning up...")
    ant.echo('running in single mode')

    def targetFolder = new File(basedir + config.directory.target + config.containerNamePrefix + 'hugeContainer.xml')

    ant.echo('building target dir...')
    ant.mkdir(dir: targetFolder)

    def index           = new File(targetFolder.absolutePath + "/Index.xml")
    def indexContent    = ''

    def xmlFile = new IndexBuilder(config, clients[0], 1)
    def int containerSerialNumber = 1

    for (client in clients) {
        ant.echo('building client number ' + containerSerialNumber)

        def coverLetter     = new File(targetFolder.absolutePath + '/' + 'Anschreiben_' + client.getContainerName() + '.xml')
        def indexText       = new IndexBuilder(config, client, containerSerialNumber)
        def coverLetterText = new LetterBuilder(config, client, containerSerialNumber)
        indexContent        = indexContent + indexText.getContent()

        coverLetter.write(coverLetterText.getContent())

        ant.copy(file: pdf.getRandom(), tofile: "${targetFolder}/Anhang_" + client.getContainerName() + '.pdf')

        containerSerialNumber++
    }

    index.write(xmlFile.getHeader() + indexContent + xmlFile.getFooter())

    ant.echo('copy and zip...')

    ant.zip(destfile: "${targetFolder}.zip", basedir: "${targetFolder}")
    ant.delete(dir: targetFolder)
}

