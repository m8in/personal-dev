package helper

class Client {

    def vorname
    def nachname
    def strasse
    def hausnummer
    def plz
    def ort
    def gebDatum
    def epbAdresse
    def containerName

    public Client (ArrayList clientData) {

        this.vorname    = clientData.get(0)
        this.nachname   = clientData.get(1)
        this.strasse    = clientData.get(2)
        this.hausnummer = clientData.get(3)
        this.plz        = clientData.get(4)
        this.ort        = clientData.get(5)
        this.gebDatum   = clientData.get(6)
        this.epbAdresse = clientData.get(7)
        this.containerName = this.epbAdresse.replace(/@/,'')
        this.containerName = this.containerName.replace('.','')

    }

    def getVorname() {
        return vorname
    }

    void setVorname(vorname) {
        this.vorname = vorname
    }

    def getNachname() {
        return nachname
    }

    void setNachname(nachname) {
        this.nachname = nachname
    }

    def getStrasse() {
        return strasse
    }

    void setStrasse(strasse) {
        this.strasse = strasse
    }

    def getHausnummer() {
        return hausnummer
    }

    void setHausnummer(hausnummer) {
        this.hausnummer = hausnummer
    }

    def getPlz() {
        return plz
    }

    void setPlz(plz) {
        this.plz = plz
    }

    def getOrt() {
        return ort
    }

    void setOrt(ort) {
        this.ort = ort
    }

    def getGebDatum() {
        return gebDatum
    }

    void setGebDatum(gebDatum) {
        this.gebDatum = gebDatum
    }

    def getEpostAdr() {
        return epostAdr
    }

    void setEpostAdr(epostAdr) {
        this.epostAdr = epostAdr
    }

    def getContainerName() {
        return containerName
    }

    void setContainerName(containerName) {
        this.containerName = containerName
    }
}
