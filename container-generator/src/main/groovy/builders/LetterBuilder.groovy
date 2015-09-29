package builders

/**
 * Created by mgroh on 02.09.15.
 */
class LetterBuilder {

    def letterText
    def replacementMap

    LetterBuilder(Map config, client, number) {
        def basedir       = new File('.').getAbsolutePath()
        this.letterText = new File(basedir + config.directory.template + 'Anschreibeninfo.xml').text

        this.replacementMap = [ ID         : 'id' + number,
                                MKGVERSION : config.mkgVersion,
                                BETREFF    : 'mkg60000 stresstest, ' + client.containerName,
                                ANSCHREIBEN: "Ein kleiner Text als Anschreiben..."]
    }

    def getContent() {

        for (entry in this.replacementMap) {
            this.letterText = this.letterText.replace(entry.key, entry.value)
        }

        return this.letterText
    }
}




