
// DO NOT EDIT THIS BLOCK BELOW=== Parameters starts ===
// PLEASE DO NOT EDIT THIS FILE

import com.cloudbees.flowpdf.StepParameters

class StartServiceParameters {
    /**
    * Label: Service Names, type: entry
    */
    String serviceNames
    /**
    * Label: Extra Arguments, type: textarea
    */
    String argString
    /**
    * Label: Wait for Started, type: checkbox
    */
    boolean waitFor

    static StartServiceParameters initParameters(StepParameters sp) {
        StartServiceParameters parameters = new StartServiceParameters()

        def serviceNames = sp.getRequiredParameter('serviceNames').value
        parameters.serviceNames = serviceNames
        def argString = sp.getRequiredParameter('argString').value
        parameters.argString = argString
        def waitFor = sp.getParameter('waitFor').value == "true"
        parameters.waitFor = waitFor

        return parameters
    }
}
// DO NOT EDIT THIS BLOCK ABOVE ^^^=== Parameters ends, checksum: 021c7a9012bff314ca9d7b1208e7f68d ===