package runtime.monitoring.casestudy;

@SuppressWarnings("all")
public class CaseStudyExampleReducedMain {
  public static void main(final String[] args) throws Exception {
    CaseStudyExampleReducedNet caseStudyExampleReducedNet = new CaseStudyExampleReducedNet();
    caseStudyExampleReducedNet.addNodes();
    caseStudyExampleReducedNet.waitForCompletion();
  }
}
