package builders

/**
 * Created by mgroh on 02.09.15.
 */
class IndexBuilder {

    def indexHeader
    def indexContent
    def indexFooter
    def replacementMap

    IndexBuilder(Map config, client, number) {
        def basedir       = new File('.').getAbsolutePath()

        this.indexHeader = new File(basedir + config.directory.template + 'IndexHeader.xml').text
        this.indexContent = new File(basedir + config.directory.template + 'IndexBrief.xml').text
        this.indexFooter = new File(basedir + config.directory.template + 'IndexFooter.xml').text

        this.replacementMap = [MKGVERSION  : config.mkgVersion,
                               VORNAME     : client.getVorname(),
                               NACHNAME    : client.getNachname(),
                               STRASSE     : client.getStrasse(),
                               HAUSNUMMER  : client.getHausnummer(),
                               POSTLEITZAHL: client.getPlz(),
                               ORT         : client.getOrt(),
                               ANSCHREIBEN : 'Anschreiben_' + client.getContainerName() + '.xml',
                               EPBADRESSE  : client.getEpbAdresse(),
                               ANHANG      : 'Anhang_' + client.getContainerName() + '.pdf',
                               ID          : 'id' + number
        ]
    }

    def getContent() {

        for (entry in this.replacementMap) {
            this.indexContent = this.indexContent.replace(entry.key, entry.value)
        }

        return indexContent
    }

    def getHeader() {
        return this.indexHeader.replace('MKGVERSION', this.replacementMap.MKGVERSION)
    }

    def getFooter() {
        return indexFooter
    }
}