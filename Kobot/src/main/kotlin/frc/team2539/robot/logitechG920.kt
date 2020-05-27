package frc.team2539.robot

class LogitechG920(id_: Int) : Benstick(id_) {

    val A = createButton(1)
    val B = createButton(2)
    val X = createButton(3)
    val Y = createButton(4)

    val RightPaddle = createButton(5)
    val LeftPaddle = createButton(6)

    val Start = createButton(7)
    val Select = createButton(8)

    val RightBumper = createButton(9)
    val LeftBumper = createButton(10)

    val XboxButton = createButton(11)

    val DPadUp = createButton(20) // DPad values may be incorrect.
    val DPadDown = createButton(22)
    val DPadRight = createButton(21)
    val DPadLeft = createButton(23)

    private var brakeVal: Double = 0.0
    private var accelVal: Double = 0.0

    private fun getWheel(): Double { return this.getRawAxis(0) }

    private fun getAccelerator(): Double { return this.getRawAxis(1) }

    private fun getBrake(): Double { return this.getRawAxis(2) }

    private fun getClutch(): Double { return this.getRawAxis(3) }

    private fun getSpeed(): Double { // Returns a negative one to one double, like a joystick. You're welcome.
        accelVal = (-getAccelerator() + 1) / 2
        brakeVal = -1 * ((-getBrake() + 1) / 2)

        return accelVal + brakeVal // Oww.
    }

    fun getLeftY(): Double { return getSpeed() } // Essentially copies and renames with a lambda for globalization purposes.
    fun getRightX(): Double { return getWheel() }

}