package runtime.monitoring.casestudy;

import klava.Locality;
import klava.Tuple;
import klava.topology.KlavaProcess;

@SuppressWarnings("all")
public class ArmControllerMoveOperation extends KlavaProcess {
  private Locality safetyLoc;

  private Locality logLoc;

  private Locality jointsLoc;

  private Coordinate target;

  private KlavaProcess afterMove;

  public ArmControllerMoveOperation(final Locality safetyLoc, final Locality logLoc, final Locality jointsLoc, final Coordinate target, final KlavaProcess afterMove) {
    this.safetyLoc = safetyLoc;
    this.logLoc = logLoc;
    this.jointsLoc = jointsLoc;
    this.target = target;
    this.afterMove = afterMove;
  }

  @Override
  public void executeProcess() {
    KlavaProcess _Proc = new KlavaProcess() {
      Coordinate target;
      Locality jointsLoc;
      KlavaProcess afterMove;
      private KlavaProcess _initFields(Coordinate target, Locality jointsLoc, KlavaProcess afterMove) {
        this.target = target;
        this.jointsLoc = jointsLoc;
        this.afterMove = afterMove;
        return this;
      }
      @Override public void executeProcess() {
        {
          out(new Tuple(new Object[] {"Move", "Normal", this.target}), this.jointsLoc);
          this.executeNodeProcess(this.afterMove);
          out(new Tuple(new Object[] {"Arm", "MoveResult", true}), this.self);
        }
      }
    }._initFields(target, jointsLoc, afterMove);
    KlavaProcess _Proc_1 = new KlavaProcess() {
      Coordinate target;
      Locality jointsLoc;
      KlavaProcess afterMove;
      private KlavaProcess _initFields(Coordinate target, Locality jointsLoc, KlavaProcess afterMove) {
        this.target = target;
        this.jointsLoc = jointsLoc;
        this.afterMove = afterMove;
        return this;
      }
      @Override public void executeProcess() {
        {
          out(new Tuple(new Object[] {"Move", "Slowly", this.target}), this.jointsLoc);
          this.executeNodeProcess(this.afterMove);
          out(new Tuple(new Object[] {"Arm", "MoveResult", true}), this.self);
        }
      }
    }._initFields(target, jointsLoc, afterMove);
    KlavaProcess _Proc_2 = new KlavaProcess() {
      Coordinate target;
      Locality jointsLoc;
      private KlavaProcess _initFields(Coordinate target, Locality jointsLoc) {
        this.target = target;
        this.jointsLoc = jointsLoc;
        return this;
      }
      @Override public void executeProcess() {
        {
          out(new Tuple(new Object[] {"Move", "Halt", this.target}), this.jointsLoc);
          out(new Tuple(new Object[] {"Arm", "MoveResult", false}), this.self);
        }
      }
    }._initFields(target, jointsLoc);
    LevelGuardedExecute _levelGuardedExecute = new LevelGuardedExecute(this.safetyLoc, this.logLoc, _Proc, _Proc_1, _Proc_2);
    this.executeNodeProcess(_levelGuardedExecute);
  }
}
