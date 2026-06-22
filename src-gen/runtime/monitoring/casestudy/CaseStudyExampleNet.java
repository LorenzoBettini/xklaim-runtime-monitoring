package runtime.monitoring.casestudy;

import klava.LogicalLocality;
import klava.PhysicalLocality;
import klava.Tuple;
import klava.topology.ClientNode;
import klava.topology.KlavaNodeCoordinator;
import klava.topology.LogicalNet;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.mikado.imc.common.IMCException;

@SuppressWarnings("all")
public class CaseStudyExampleNet extends LogicalNet {
  private static final LogicalLocality CaseStudyExampleNormal = new LogicalLocality("CaseStudyExampleNormal");

  public static class CaseStudyExampleNormal extends ClientNode {
    private static class CaseStudyExampleNormalProcess extends KlavaNodeCoordinator {
      @Override
      public void executeProcess() {
        try {
          final PhysicalLocality monitorLoc = this.newloc();
          final PhysicalLocality safetyLoc = this.newloc();
          final PhysicalLocality logLoc = this.newloc();
          final PhysicalLocality jointsLoc = this.newloc();
          PhysicalLocality gripperLoc = this.newloc();
          out(new Tuple(new Object[] {"Level", 0}), safetyLoc);
          out(new Tuple(new Object[] {"NormalRestored"}), monitorLoc);
          Thread.sleep(1000);
          ArmMonitor _armMonitor = new ArmMonitor(monitorLoc, safetyLoc, logLoc);
          eval(_armMonitor, this.self);
          Thread.sleep(1000);
          ArmRobot _armRobot = new ArmRobot(jointsLoc, gripperLoc);
          eval(_armRobot, this.self);
          Coordinate _coordinate = new Coordinate("1.0, 2.0, 3.0");
          Coordinate _coordinate_1 = new Coordinate("4.0, 5.0, 6.0");
          ArmController _armController = new ArmController(safetyLoc, logLoc, jointsLoc, gripperLoc, _coordinate, _coordinate_1);
          eval(_armController, this.self);
          Thread.sleep(1000);
          in(new Tuple(new Object[] {"ArmController", "Pick", "OK"}), logLoc);
          in(new Tuple(new Object[] {"ArmController", "Place", "OK"}), logLoc);
          InputOutput.<String>println("FINISH");
          this.done();
        } catch (Throwable _e) {
          throw Exceptions.sneakyThrow(_e);
        }
      }
    }

    public CaseStudyExampleNormal() {
      super(new PhysicalLocality("localhost:9999"), new LogicalLocality("CaseStudyExampleNormal"));
    }

    public void addMainProcess() throws IMCException {
      KlavaNodeCoordinator _coordinator = new CaseStudyExampleNet.CaseStudyExampleNormal.CaseStudyExampleNormalProcess();
      setMainCoordinator(_coordinator);
      addNodeCoordinator(_coordinator);
    }
  }

  public CaseStudyExampleNet() throws IMCException {
    super(new PhysicalLocality("localhost:9999"));
  }

  public void addNodes() throws IMCException {
    CaseStudyExampleNet.CaseStudyExampleNormal caseStudyExampleNormal = new CaseStudyExampleNet.CaseStudyExampleNormal();
    addManagedNode(caseStudyExampleNormal);
    caseStudyExampleNormal.addMainProcess();
  }
}
