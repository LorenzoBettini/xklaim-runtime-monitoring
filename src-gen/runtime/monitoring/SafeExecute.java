package runtime.monitoring;

import java.util.List;
import klava.Locality;
import klava.Tuple;
import klava.topology.KlavaOrProcess;
import klava.topology.KlavaProcess;
import org.eclipse.xtext.xbase.lib.InputOutput;

@SuppressWarnings("all")
public class SafeExecute extends KlavaProcess {
  private KlavaProcess P;

  private Locality safetyLoc;

  public SafeExecute(final KlavaProcess P, final Locality safetyLoc) {
    this.P = P;
    this.safetyLoc = safetyLoc;
  }

  @Override
  public void executeProcess() {
    KlavaOrProcess _OrProc = new KlavaOrProcess() {
      Locality safetyLoc;
      KlavaProcess P;
      private KlavaOrProcess _initFields(Locality safetyLoc, KlavaProcess P) {
        this.safetyLoc = safetyLoc;
        this.P = P;
        return this;
      }
      @Override public void executeProcess() {
        {
          read(new Tuple(new Object[] {"Clear"}), this.safetyLoc);
          InputOutput.<String>println("SAFE EXECUTE: Clear");
          this.executeNodeProcess(this.P);
        }
      }
    }._initFields(safetyLoc, P);
    KlavaOrProcess _OrProc_1 = new KlavaOrProcess() {
      Locality safetyLoc;
      KlavaProcess P;
      private KlavaOrProcess _initFields(Locality safetyLoc, KlavaProcess P) {
        this.safetyLoc = safetyLoc;
        this.P = P;
        return this;
      }
      @Override public void executeProcess() {
        {
          read(new Tuple(new Object[] {"Stop"}), this.safetyLoc);
          InputOutput.<String>println("SAFE EXECUTE: Stop");
          read(new Tuple(new Object[] {"Clear"}), this.safetyLoc);
          InputOutput.<String>println("SAFE EXECUTE: Clear Again");
          this.executeNodeProcess(this.P);
        }
      }
    }._initFields(safetyLoc, P);
    or(List.of(_OrProc, _OrProc_1));
  }
}
