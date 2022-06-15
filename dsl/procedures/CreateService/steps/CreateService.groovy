$[/myProject/groovy/scripts/preamble.groovy.ignore]

WindowsServiceControl plugin = new WindowsServiceControl()
plugin.runStep('Create Service', 'Create Service', 'createService')