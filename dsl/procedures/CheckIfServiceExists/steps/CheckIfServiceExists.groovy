$[/myProject/groovy/scripts/preamble.groovy.ignore]

WindowsServiceControl plugin = new WindowsServiceControl()
plugin.runStep('Check If Service Exists', 'Check If Service Exists', 'checkIfServiceExists')