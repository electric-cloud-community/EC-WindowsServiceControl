$[/myProject/groovy/scripts/preamble.groovy.ignore]

WindowsServiceControl plugin = new WindowsServiceControl()
plugin.runStep('Disable Service', 'Disable Service', 'disableService')