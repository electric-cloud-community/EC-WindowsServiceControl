$[/myProject/groovy/scripts/preamble.groovy.ignore]

WindowsServiceControl plugin = new WindowsServiceControl()
plugin.runStep('Stop Service', 'Stop Service', 'stopService')