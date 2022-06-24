import com.cloudbees.flowpdf.*
import com.cloudbees.flowpdf.components.ComponentManager
import com.cloudbees.flowpdf.components.cli.*

/**
* WindowsServiceControl
*/
class WindowsServiceControl extends FlowPlugin {

    @Override
    Map<String, Object> pluginInfo() {
        return [
                pluginName     : '@PLUGIN_KEY@',
                pluginVersion  : '@PLUGIN_VERSION@',
                configFields   : ['config'],
                configLocations: ['ec_plugin_cfgs'],
                defaultConfigValues: [:]
        ]
    }
// === check connection ends ===
/**
    * createService - Create Service/Create Service
    * Add your code into this method and it will be called when the step runs
    * @param serviceName (required: true)
    * @param argString (required: false)
    
    */
    def createService(StepParameters p, StepResult sr) {
        // Use this parameters wrapper for convenient access to your parameters
        CreateServiceParameters sp = CreateServiceParameters.initParameters(p)

        // Calling logger:
        log.info p.asMap.get('serviceName')
        log.info p.asMap.get('argString')
        def argString = p.asMap.get('argString')
        def argStrings = (argString?argString.split('\n'):null)
        def serviceName = p.asMap.get('serviceName')
        def summaryMessage = "Service '" + serviceName + "' created successfully.\n"
        def args = ['create', serviceName]
        if(argStrings){
            args.addAll(argStrings)
        }
        ExecutionResult result = runSCCommand(args)

        // Setting job step summary to the config name
        if(!result.isSuccess()) {
            summaryMessage = "Failed to create service '" + serviceName + "'.\n"
            sr.setJobStepOutcome('error')
        }

        sr.setJobStepSummary(summaryMessage)

        sr.apply()
        log.info("step Create Service has been finished")
    }

/**
    * checkIfServiceExists - Check If Service Exists/Check If Service Exists
    * Add your code into this method and it will be called when the step runs
    * @param serviceNames (required: true)
    
    */
    def checkIfServiceExists(StepParameters p, StepResult sr) {
        // Use this parameters wrapper for convenient access to your parameters
        CheckIfServiceExistsParameters sp = CheckIfServiceExistsParameters.initParameters(p)

        // Calling logger:
        log.info p.asMap.get('serviceNames')

        String serviceNames =  p.asMap.get('serviceNames')
        String[] serviceNamesList = serviceNames.split(',')
        def failed = false
        def summaryMessage = ''

        for( String  serviceName : serviceNamesList){
            ExecutionResult result = runSCCommand([
                'query',
                serviceName
            ])
            if(!result.isSuccess()) {
                failed = true
                summaryMessage += "Service '" + serviceName + "' does not exist\n"
            } else {
                summaryMessage += "Service '" + serviceName + "' exists\n"
            }
        }

        // Setting job step summary
        sr.setJobStepSummary(summaryMessage)

        if(failed) {
            sr.setJobStepOutcome('error')
        }
        sr.apply()
        log.info("step Check If Service Exists has been finished")
    }

/**
    * deleteService - Delete Service/Delete Service
    * Add your code into this method and it will be called when the step runs
    * @param serviceNames (required: true)
    * @param argString (required: false)
    
    */
    def deleteService(StepParameters p, StepResult sr) {
        // Use this parameters wrapper for convenient access to your parameters
        DeleteServiceParameters sp = DeleteServiceParameters.initParameters(p)

        // Calling logger:
        log.info p.asMap.get('serviceNames')
        log.info p.asMap.get('argString')

        def argString = p.asMap.get('argString')
        def argStrings = (argString?argString.split('\n'):null)

        String serviceNames =  p.asMap.get('serviceNames')
        String[] serviceNamesList = serviceNames.split(',')

        def failed = false
        def summaryMessage = ''

        for( String  serviceName : serviceNamesList){
            def args = ['delete', serviceName]
            if(argStrings){
                args.addAll(argStrings)
            }
            ExecutionResult result = runSCCommand(args)
            if(!result.isSuccess()) {
                failed = true
                summaryMessage += "Cannot delete service '" + serviceName + "'\n"
            } else {
                summaryMessage += "Service '" + serviceName + "' deleted.\n"
            }
        }

        // Setting job step summary
        sr.setJobStepSummary(summaryMessage)

        if(failed) {
            sr.setJobStepOutcome('error')
        }
        sr.apply()
        log.info("step Delete Service has been finished")
    }

/**
    * disableService - Disable Service/Disable Service
    * Add your code into this method and it will be called when the step runs
    * @param serviceNames (required: true)
    * @param argString (required: false)
    
    */
    def disableService(StepParameters p, StepResult sr) {
        // Use this parameters wrapper for convenient access to your parameters
        DisableServiceParameters sp = DisableServiceParameters.initParameters(p)

        // Calling logger:
        log.info p.asMap.get('serviceNames')
        log.info p.asMap.get('argString')

        def argString = p.asMap.get('argString')
        def argStrings = (argString?argString.split('\n'):null)

        String serviceNames =  p.asMap.get('serviceNames')
        String[] serviceNamesList = serviceNames.split(',')

        def failed = false
        def summaryMessage = ''

        for( String  serviceName : serviceNamesList){
            def args = ['config', serviceName, "start=", "disabled"]
            if(argStrings){
                args.addAll(argStrings)
            }
            ExecutionResult result = runSCCommand(args)
            if(!result.isSuccess()) {
                failed = true
                summaryMessage += "Cannot disable service '" + serviceName + "'\n"
            } else {
                summaryMessage += "Service '" + serviceName + "' disabled.\n"
            }
        }

        // Setting job step summary
        sr.setJobStepSummary(summaryMessage)

        if(failed) {
            sr.setJobStepOutcome('error')
        }

        sr.apply()
        log.info("step Disable Service has been finished")
    }

/**
    * enableService - Enable Service/Enable Service
    * Add your code into this method and it will be called when the step runs
    * @param serviceNames (required: true)
    * @param argString (required: false)
    * @param startType (required: true)
    
    */
    def enableService(StepParameters p, StepResult sr) {
        // Use this parameters wrapper for convenient access to your parameters
        EnableServiceParameters sp = EnableServiceParameters.initParameters(p)

        // Calling logger:
        log.info p.asMap.get('serviceNames')
        log.info p.asMap.get('argString')
        log.info p.asMap.get('startType')

        def startType = p.asMap.get('startType')
        def argString = p.asMap.get('argString')
        def argStrings = (argString?argString.split('\n'):null)

        String serviceNames =  p.asMap.get('serviceNames')
        String[] serviceNamesList = serviceNames.split(',')

        def failed = false
        def summaryMessage = ''

        for( String  serviceName : serviceNamesList){
            def args = ['config', serviceName, "start=", startType]
            if(argStrings){
                args.addAll(argStrings)
            }
            ExecutionResult result = runSCCommand(args)
            if(!result.isSuccess()) {
                failed = true
                summaryMessage += "Cannot enable service '" + serviceName + "'\n"
            } else {
                summaryMessage += "Service '" + serviceName + "' enabled.\n"
            }
        }

        // Setting job step summary
        sr.setJobStepSummary(summaryMessage)

        if(failed) {
            sr.setJobStepOutcome('error')
        }

        sr.apply()
        log.info("step Enable Service has been finished")
    }

/**
    * startService - Start Service/Start Service
    * Add your code into this method and it will be called when the step runs
    * @param serviceNames (required: true)
    * @param argString (required: false)
    * @param waitFor (required: false)
    
    */
    def startService(StepParameters p, StepResult sr) {
        // Use this parameters wrapper for convenient access to your parameters
        StartServiceParameters sp = StartServiceParameters.initParameters(p)

        // Calling logger:
        log.info p.asMap.get('serviceNames')
        log.info p.asMap.get('argString')
        log.info p.asMap.get('waitFor')
        

        // Setting job step summary to the config name
        sr.setJobStepSummary(p.getParameter('config')?.getValue() ?: 'null')

        sr.setReportUrl("Sample Report", 'https://cloudbees.com')
        sr.apply()
        log.info("step Start Service has been finished")
    }

/**
    * stopService - Stop Service/Stop Service
    * Add your code into this method and it will be called when the step runs
    * @param serviceNames (required: true)
    * @param waitFor (required: false)
    * @param timeout (required: false)
    
    */
    def stopService(StepParameters p, StepResult sr) {
        // Use this parameters wrapper for convenient access to your parameters
        StopServiceParameters sp = StopServiceParameters.initParameters(p)

        // Calling logger:
        log.info p.asMap.get('serviceNames')
        log.info p.asMap.get('waitFor')
        log.info p.asMap.get('timeout')
        

        // Setting job step summary to the config name
        sr.setJobStepSummary(p.getParameter('config')?.getValue() ?: 'null')

        sr.setReportUrl("Sample Report", 'https://cloudbees.com')
        sr.apply()
        log.info("step Stop Service has been finished")
    }

/**
    * checkServiceStatus - Check Service Status/Check Service Status
    * Add your code into this method and it will be called when the step runs
    * @param serviceNames (required: true)
    
    */
    def checkServiceStatus(StepParameters p, StepResult sr) {
        // Use this parameters wrapper for convenient access to your parameters
        CheckServiceStatusParameters sp = CheckServiceStatusParameters.initParameters(p)

        // Calling logger:
        log.info p.asMap.get('serviceNames')

        String serviceNames =  p.asMap.get('serviceNames')
        String[] serviceNamesList = serviceNames.split(',')

        def failed = false
        def summaryMessage = ''

        for( String  serviceName : serviceNamesList){
            def args = ['query', serviceName]
            ExecutionResult result = runSCCommand(args)
            if(!result.isSuccess()) {
                failed = true
                summaryMessage += "Failed to check service '" + serviceName + "' status\n"
            } else {
                if(result.getStdOut().contains("RUNNING")){
                    summaryMessage += "Service '" + serviceName + "' is running.\n"
                } else if (result.getStdOut().contains("STOPPED")){
                    failed = true
                    summaryMessage += "Service '" + serviceName + "' is stopped.\n"
                } else if (result.getStdOut().contains("PAUSED")){
                    failed = true
                    summaryMessage += "Service '" + serviceName + "' is paused.\n"
                } else {
                    failed = true
                    summaryMessage += "Service '" + serviceName + "' status is unrecognized, Exiting with failure.\n"
                }
            }
        }

        // Setting job step summary
        sr.setJobStepSummary(summaryMessage)

        if(failed) {
            sr.setJobStepOutcome('error')
        }

        sr.apply()
        log.info("step Check Service Status has been finished")
    }

// === step ends ===
    /**
        * runSCCommand - Run SC Command/Run SC Command
        * @param args (required: true)

    */
    def runSCCommand(def args){
        String executableName = 'sc.exe'
        String workspaceDir = System.getProperty('user.dir')
        /** Instantiating CLI component with a ComponentManager */
        CLI cli = (CLI) ComponentManager.loadComponent(CLI.class, [workingDirectory: workspaceDir], this)

        /** Creating a Command instance */
        Command command = cli.newCommand(executableName, args as ArrayList<String>)
        log.info "\nCommand to run is " + command.renderCommand().command().join(' ')

        /** ExecutsetJobStepSummarying the command */
        ExecutionResult result = cli.runCommand(command)

        log.info "stdout: " + result.getStdOut()
        log.info "stderr: " + result.getStdErr()
        log.info "return code: " + result.getCode()
        return result
    }

}