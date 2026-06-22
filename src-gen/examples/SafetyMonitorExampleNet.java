package examples;

import klava.LogicalLocality;
import klava.PhysicalLocality;
import klava.Tuple;
import klava.topology.ClientNode;
import klava.topology.KlavaNodeCoordinator;
import klava.topology.KlavaProcess;
import klava.topology.LogicalNet;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.mikado.imc.common.IMCException;
import runtime.monitoring.SafeExecute;
import runtime.monitoring.SafetyMonitorImperative;

@SuppressWarnings("all")
public class SafetyMonitorExampleNet extends LogicalNet {
  private static final LogicalLocality SafetyMonitorExample = new LogicalLocality("SafetyMonitorExample");

  public static class SafetyMonitorExample extends ClientNode {
    private static class SafetyMonitorExampleProcess extends KlavaNodeCoordinator {
      @Override
      public void executeProcess() {
        try {
          final PhysicalLocality monitorLoc = this.newloc();
          final PhysicalLocality safetyLoc = this.newloc();
          out(new Tuple(new Object[] {"Clear"}), safetyLoc);
          SafetyMonitorImperative _safetyMonitorImperative = new SafetyMonitorImperative(monitorLoc, safetyLoc);
          eval(_safetyMonitorImperative, this.self);
          InputOutput.<String>println("out: SafetyConditionDetected");
          out(new Tuple(new Object[] {"SafetyConditionDetected"}), monitorLoc);
          InputOutput.<String>println("out: SafetyConditionDetected");
          out(new Tuple(new Object[] {"SafetyConditionDetected"}), monitorLoc);
          InputOutput.<String>println("out: SafetyConditionDetected");
          out(new Tuple(new Object[] {"SafetyConditionDetected"}), monitorLoc);
          KlavaProcess _Proc = new KlavaProcess() {
            PhysicalLocality safetyLoc;
            private KlavaProcess _initFields(PhysicalLocality safetyLoc) {
              this.safetyLoc = safetyLoc;
              return this;
            }
            @Override public void executeProcess() {
              try {
                {
                  InputOutput.<String>println("CriticalProcess: non critical action");
                  InputOutput.<String>println("CriticalProcess: about to perform critical action");
                  KlavaProcess _Proc_1 = new KlavaProcess() {
                    private KlavaProcess _initFields() {
                      return this;
                    }
                    @Override public void executeProcess() {
                      {
                        InputOutput.<String>println("CriticalProcess: perform critical action");
                        out(new Tuple(new Object[] {"Finish"}), this.self);
                      }
                    }
                  }._initFields();
                  SafeExecute _safeExecute = new SafeExecute(_Proc_1, safetyLoc);
                  this.executeNodeProcess(_safeExecute);
                }
              } catch (Throwable _e) {
                throw Exceptions.sneakyThrow(_e);
              }
            }
          }._initFields(safetyLoc);
          eval(_Proc, this.self);
          Thread.sleep(2000);
          InputOutput.<String>println("out: SafetyConditionCleared");
          out(new Tuple(new Object[] {"SafetyConditionCleared"}), monitorLoc);
          InputOutput.<String>println("out: SafetyConditionCleared");
          out(new Tuple(new Object[] {"SafetyConditionCleared"}), monitorLoc);
          InputOutput.<String>println("out: SafetyConditionCleared");
          out(new Tuple(new Object[] {"SafetyConditionCleared"}), monitorLoc);
          in(new Tuple(new Object[] {"Finish"}), this.self);
          InputOutput.<String>println("FINISH");
          System.exit(0);
        } catch (Throwable _e_1) {
          throw Exceptions.sneakyThrow(_e_1);
        }
      }
    }

    public SafetyMonitorExample() {
      super(new PhysicalLocality("localhost:9999"), new LogicalLocality("SafetyMonitorExample"));
    }

    public void addMainProcess() throws IMCException {
      KlavaNodeCoordinator _coordinator = new SafetyMonitorExampleNet.SafetyMonitorExample.SafetyMonitorExampleProcess();
      setMainCoordinator(_coordinator);
      addNodeCoordinator(_coordinator);
    }
  }

  public SafetyMonitorExampleNet() throws IMCException {
    super(new PhysicalLocality("localhost:9999"));
  }

  public void addNodes() throws IMCException {
    SafetyMonitorExampleNet.SafetyMonitorExample safetyMonitorExample = new SafetyMonitorExampleNet.SafetyMonitorExample();
    addManagedNode(safetyMonitorExample);
    safetyMonitorExample.addMainProcess();
  }
}
