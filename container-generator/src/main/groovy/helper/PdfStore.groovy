package helper
/**
 * Created by mgroh on 21.09.15.
 */
class PdfStore {

    def repo = []

    public initialize(Map config) {
        def basedir  = new File('.').getAbsolutePath()
        def dir      = new File(basedir + config.directory.pdf)
        def fileList = dir.listFiles()

        fileList.each { if (it.size()/1024/1024 < config.maxPdfSize) this.repo.add(it) }

    }

    public getRandom() {
        def rand    = new Random()
        int index   = rand.nextInt(this.repo.size())
        return this.repo[index]
    }

}
