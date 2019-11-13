package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "StraferDriving (Blocks to Java)", group = "")
public class StraferDriving extends LinearOpMode {

  private DcMotor rightFront;
  private DcMotor rightBack;
  private DcMotor leftFront;
  private DcMotor leftBack;
  private Servo leftHand;

  /**
   * This function is executed when this Op Mode is selected from the Driver Station.
   */
  @Override
  public void runOpMode() {
    double servo_Speed;
    Servo.Direction servo_Position;
    double ServoPositionRightHand;
    double ServoSpeedRightHand;

    rightFront = hardwareMap.dcMotor.get("rightFront");
    rightBack = hardwareMap.dcMotor.get("rightBack");
    leftFront = hardwareMap.dcMotor.get("leftFront");
    leftBack = hardwareMap.dcMotor.get("leftBack");
    leftHand = hardwareMap.servo.get("leftHand");

    // Put initialization blocks here.
    // Set servo to mid position
    servo_Speed = 0.01;
    servo_Position = 0.5;
    ServoPositionRightHand = 0.5;
    ServoSpeedRightHand = 0.01;
    rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
    rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
    waitForStart();
    if (opModeIsActive()) {
      // Put run blocks here.
      while (opModeIsActive()) {
        // Put loop blocks here.
        rightFront.setPower(-gamepad1.left_stick_x + (-gamepad1.left_stick_y - gamepad1.right_stick_x));
        rightBack.setPower(gamepad1.left_stick_x + (-gamepad1.left_stick_y - gamepad1.right_stick_x));
        leftFront.setPower(gamepad1.left_stick_x + -gamepad1.left_stick_y + gamepad1.right_stick_x);
        leftBack.setPower(-gamepad1.left_stick_x + -gamepad1.left_stick_y + gamepad1.right_stick_x);
        if (gamepad1.b) {
          ServoPositionRightHand += ServoSpeedRightHand;
        }
        if (gamepad1.x) {
          ServoPositionRightHand += -ServoSpeedRightHand;
        }
        if (gamepad1.b) {
          servo_Position += servo_Speed;
        }
        if (gamepad1.x) {
          servo_Position += -servo_Speed;
        }
        servo_Position = Math.min(Math.max(servo_Position, 0), 1);
        ServoPositionRightHand = Math.min(Math.max(servo_Position, 0), 1);
        leftHand.setDirection(servo_Position);
        telemetry.addData("RightFrontPow", rightFront.getPower());
        telemetry.addData("RightBackPow", rightBack.getPower());
        telemetry.addData("LeftFrontPow", leftFront.getPower());
        telemetry.addData("LeftBackPow", leftBack.getPower());
        telemetry.addData("ServoLeftHand", servo_Position);
        telemetry.addData("ServoRightHand", ServoPositionRightHand);
        telemetry.update();
      }
    }
  }
}
