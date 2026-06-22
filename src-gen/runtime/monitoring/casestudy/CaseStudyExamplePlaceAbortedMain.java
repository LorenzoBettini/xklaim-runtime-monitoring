package runtime.monitoring.casestudy;

@SuppressWarnings("all")
public class CaseStudyExamplePlaceAbortedMain {
  public static void main(final String[] args) throws Exception {
    CaseStudyExamplePlaceAbortedNet caseStudyExamplePlaceAbortedNet = new CaseStudyExamplePlaceAbortedNet();
    caseStudyExamplePlaceAbortedNet.addNodes();
    caseStudyExamplePlaceAbortedNet.waitForCompletion();
  }
}
