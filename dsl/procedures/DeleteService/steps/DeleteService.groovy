$[/myProject/groovy/scripts/preamble.groovy.ignore]

WindowsServiceControl plugin = new WindowsServiceControl()
plugin.runStep('Delete Service', 'Delete Service', 'deleteService')