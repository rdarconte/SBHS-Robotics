// Commands to drive forward in Tank Mode (Autonomous Mode)
// for short time

package frc.robot.autos;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;

public class DriveAroundAuto extends Command {
    private DriveSubsystem m_drive;
    private Timer timer;
    private double drive_seconds = 2.25;

      /**
     * @param drive
     */

    public DriveAroundAuto(DriveSubsystem drive)
    {
        m_drive = drive;
        
        timer = new Timer();

        addRequirements(m_drive);
    }

    @Override
  public void initialize() {
    // start timer, uses restart to clear the timer as well, in case this command has
    // already been run before
    timer.restart();
  }

  // Runs every cycle while the command is scheduled (~50 times per second), here we will just drive forwards
  @Override
  public void execute() {

    if(timer.get() < drive_seconds)
    {

        m_drive.driveTank(0.5, 0.5, false);

    }

}

@Override
public void end(boolean isInterrupted) {
  // stop drive motors
  m_drive.driveArcade(0.0, 0.0, false);
  timer.stop();
}

// Runs every cycle while the command is scheduled to check if the command is
// finished
@Override
public boolean isFinished() {
  // check if timer exceeds seconds, when it has this will return true indicating
  // this command is finished
  return timer.get() >= drive_seconds;
}
}
