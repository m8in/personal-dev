package helper

import org.yaml.snakeyaml.DumperOptions
import org.yaml.snakeyaml.Yaml

/**
 * Created by mgroh on 22.09.15.
 */
class ConfigBuilder {

    def config
    def yaml
    def String configFile

    ConfigBuilder() {

        this.configFile = new File(System.getProperty("user.dir") + '/../resources/configuration/configuration.yaml').getAbsolutePath()
        def file = new File(this.configFile).getText()

        final dumperOptions = new DumperOptions()
        dumperOptions.setIndent(4);
        dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        this.yaml = new Yaml(dumperOptions)

        this.config = this.yaml.load(file)
    }

    public getConfig() {
        return this.config
    }

    public setConfigParam(List options) {

        if (this.config.containsKey(options[0])) {
            this.config[options[0]] = options[1]
            writeConfig()
            println('config property set. New config looks like this:\n')
            showConfig()
        } else {
            println("ERROR: config property named '" + options[0] + "' is invalid!")
        }
    }

    public showConfig() {
        String output = this.yaml.dump(this.config);
        println('Configuration settings:\n\n' + output);
    }

    private writeConfig() {
        FileWriter writer = new FileWriter(this.configFile);
        this.yaml.dump(this.config, writer)
    }
}
