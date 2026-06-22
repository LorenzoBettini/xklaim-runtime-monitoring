package runtime.monitoring;

import java.util.List;
import klava.Locality;
import klava.Tuple;
import klava.topology.KlavaOrProcess;
import klava.topology.KlavaProcess;
import org.eclipse.xtext.xbase.lib.InputOutput;

@SuppressWarnings("all")
public class SafetyMonitor extends KlavaProcess {
  private Locality monitorLoc;

  private Locality safetyLoc;

  public SafetyMonitor(final Locality monitorLoc, final Locality safetyLoc) {
    this.monitorLoc = monitorLoc;
    this.safetyLoc = safetyLoc;
  }

  @Override
  public void executeProcess() {
    while ((!Thread.interrupted())) {
      KlavaOrProcess _OrProc = new KlavaOrProcess() {
        Locality monitorLoc;
        Locality safetyLoc;
        private KlavaOrProcess _initFields(Locality monitorLoc, Locality safetyLoc) {
          this.monitorLoc = monitorLoc;
          this.safetyLoc = safetyLoc;
          return this;
        }
        @Override public void executeProcess() {
          {
            in(new Tuple(new Object[] {"SafetyConditionDetected"}), this.monitorLoc);
            InputOutput.<String>println("SAFETY MONITOR: SafetyConditionDetected");
            KlavaOrProcess _OrProc_1 = new KlavaOrProcess() {
              Locality safetyLoc;
              private KlavaOrProcess _initFields(Locality safetyLoc) {
                this.safetyLoc = safetyLoc;
                return this;
              }
              @Override public void executeProcess() {
                {
                  in(new Tuple(new Object[] {"Clear"}), this.safetyLoc);
                  out(new Tuple(new Object[] {"Stop"}), this.safetyLoc);
                  InputOutput.<String>println("SAFETY MONITOR: Stop");
                }
              }
            }._initFields(safetyLoc);
            KlavaOrProcess _OrProc_2 = new KlavaOrProcess() {
              Locality safetyLoc;
              private KlavaOrProcess _initFields(Locality safetyLoc) {
                this.safetyLoc = safetyLoc;
                return this;
              }
              @Override public void executeProcess() {
                {
                  read(new Tuple(new Object[] {"Stop"}), this.safetyLoc);
                  InputOutput.<String>println("SAFETY MONITOR: Already Stop");
                }
              }
            }._initFields(safetyLoc);
            or(List.of(_OrProc_1, _OrProc_2));
          }
        }
      }._initFields(monitorLoc, safetyLoc);
      KlavaOrProcess _OrProc_1 = new KlavaOrProcess() {
        Locality monitorLoc;
        Locality safetyLoc;
        private KlavaOrProcess _initFields(Locality monitorLoc, Locality safetyLoc) {
          this.monitorLoc = monitorLoc;
          this.safetyLoc = safetyLoc;
          return this;
        }
        @Override public void executeProcess() {
          {
            in(new Tuple(new Object[] {"SafetyConditionCleared"}), this.monitorLoc);
            InputOutput.<String>println("SAFETY MONITOR: SafetyConditionCleared");
            KlavaOrProcess _OrProc_2 = new KlavaOrProcess() {
              Locality safetyLoc;
              private KlavaOrProcess _initFields(Locality safetyLoc) {
                this.safetyLoc = safetyLoc;
                return this;
              }
              @Override public void executeProcess() {
                {
                  in(new Tuple(new Object[] {"Stop"}), this.safetyLoc);
                  out(new Tuple(new Object[] {"Clear"}), this.safetyLoc);
                  InputOutput.<String>println("SAFETY MONITOR: Clear");
                }
              }
            }._initFields(safetyLoc);
            KlavaOrProcess _OrProc_3 = new KlavaOrProcess() {
              Locality safetyLoc;
              private KlavaOrProcess _initFields(Locality safetyLoc) {
                this.safetyLoc = safetyLoc;
                return this;
              }
              @Override public void executeProcess() {
                {
                  read(new Tuple(new Object[] {"Clear"}), this.safetyLoc);
                  InputOutput.<String>println("SAFETY MONITOR: Already Clear");
                }
              }
            }._initFields(safetyLoc);
            or(List.of(_OrProc_2, _OrProc_3));
          }
        }
      }._initFields(monitorLoc, safetyLoc);
      or(List.of(_OrProc, _OrProc_1));
    }
  }
}
