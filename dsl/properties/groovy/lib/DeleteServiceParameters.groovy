
// DO NOT EDIT THIS BLOCK BELOW=== Parameters starts ===
// PLEASE DO NOT EDIT THIS FILE

import com.cloudbees.flowpdf.StepParameters

class DeleteServiceParameters {
    /**
    * Label: Service Names, type: entry
    */
    String serviceNames
    /**
    * Label: Extra Arguments, type: textarea
    */
    String argString

    static DeleteServiceParameters initParameters(StepParameters sp) {
        DeleteServiceParameters parameters = new DeleteServiceParameters()

        def serviceNames = sp.getRequiredParameter('serviceNames').value
        parameters.serviceNames = serviceNames
        def argString = sp.getParameter('argString').value
        parameters.argString = argString

        return parameters
    }
}
// DO NOT EDIT THIS BLOCK ABOVE ^^^=== Parameters ends, checksum: 9ef426d443cc786f4b66e4a7c46ce7f7 ===
