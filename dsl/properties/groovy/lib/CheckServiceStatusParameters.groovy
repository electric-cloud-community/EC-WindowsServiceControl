
// DO NOT EDIT THIS BLOCK BELOW=== Parameters starts ===
// PLEASE DO NOT EDIT THIS FILE

import com.cloudbees.flowpdf.StepParameters

class CheckServiceStatusParameters {
    /**
    * Label: Service Names, type: entry
    */
    String serviceNames

    static CheckServiceStatusParameters initParameters(StepParameters sp) {
        CheckServiceStatusParameters parameters = new CheckServiceStatusParameters()

        def serviceNames = sp.getRequiredParameter('serviceNames').value
        parameters.serviceNames = serviceNames

        return parameters
    }
}
// DO NOT EDIT THIS BLOCK ABOVE ^^^=== Parameters ends, checksum: 8d233e7c940c29ef6002b9fa9188245f ===
