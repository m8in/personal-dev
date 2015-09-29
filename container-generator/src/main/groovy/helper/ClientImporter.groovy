package helper
/**
 * Created by mgroh on 03.09.15.
 */
class ClientImporter {

    def clients = []

    public ClientImporter(Map config) {

        def basedir       = new File('.').getAbsolutePath()
        def String inputFile = new File(basedir + config.directory.data + config.inputFile)

        def germanReplacements = ['Ã' : 'ß']

        new File(inputFile).eachLine { row ->

            if (row =~ /.*@epsotbriefe.com.*/) {
                for (entry in germanReplacements) {
                    row = row.replace(entry.key, entry.value)
                }
                def data = row.tokenize('~')
                data.remove(0)

                def client = new Client(data.collate(8).get(0))

                this.clients.add(client)
            }
        }
    }

    def getClients() {
        return clients
    }

    void setClients(clients) {
        this.clients = clients
    }
}

