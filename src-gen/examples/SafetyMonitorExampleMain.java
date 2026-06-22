package examples;

@SuppressWarnings("all")
public class SafetyMonitorExampleMain {
  public static void main(final String[] args) throws Exception {
    SafetyMonitorExampleNet safetyMonitorExampleNet = new SafetyMonitorExampleNet();
    safetyMonitorExampleNet.addNodes();
    safetyMonitorExampleNet.waitForCompletion();
  }
}
