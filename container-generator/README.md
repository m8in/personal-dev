# How to use this stuff...

Change to directory "src/main/groovy" and execute "groovy Container.groovy" with the option of your choice.
If you don't know, what option "-h" would be a good start.
All configurations are read from the file "src/main/resources/configuration/configuration.yaml" and can be
manipulated by the "-p" option or in the file itself.
The input file is base for all generated containers. This should be a dumpfile from the userservice of the
testenvironment. This is important so that the users are registered for ANQS at DPZ.
Ask operations (M. Klinger) for details. He knows what to do... ;-)

## Prerequisites
After checkout run an "maven clean install" from the commandline. After that, create a ".groovy/lib" directory in
your homedirectory should it not already exist. Change dir to "~/.groovy/lib" and run the following as sudo:
    "ln -s ~/.m2/repository/org/yaml/snakeyaml/1.16/snakeyaml-1.16.jar ."  (the final "." is important!)

## All available options:

mkgVersion:             guess what this means
containerNamePrefix:    all container names will be prefixed with this e.g. "mkg60000_blabla.zip".
maxPdfSize:             max attachementsize in MB. Feel free to add attachements to the directory.pdf
inputFile:              name of the input file
domainName:             self explanatory. '@epsotbriefe.com', '@epost.de', 'testingepost.de'.... the choice is yours
directory:
    data:       here be input data
    template:   the xml templates
    pdf:        possible attachements
    target:     your generated containers ready to import!
