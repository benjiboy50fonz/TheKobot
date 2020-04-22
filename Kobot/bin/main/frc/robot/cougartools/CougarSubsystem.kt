package frc.robot.cougartools

import edu.wpi.first.wpilibj2.command.SubsystemBase

import kotlinx.coroutines.*

abstract class CougarSubsystem : SubsystemBase() {

    // A special, FRC 2539 parent class that will utilize coroutines.

    open fun doWhileRunning(_delayingTask: () -> Unit, _waitingTask: () -> Unit ) = runBlocking{

        /* Offers the ability to do "_waitingTask", while "_delayingTask" is finishing. Neither can
        *  currently take arguments however. Delaying task should take longer!
        */

        val myJob = launch {
            _delayingTask()
        }
        _waitingTask()
        myJob.join() // Waits for myJob to finish.

        // Better to use delay(), doesn't block threads. Just a side note :).

    }

    open fun doWhileRunningWithTimeout(_delayingTask: () -> Unit, _waitingTask: () -> Unit, timeOut: Double) = runBlocking{

        /* Offers the ability to run two tasks, and if one finishes, a time left for the other begins! Still no parameters.
        *  timeOut is the delay before cancel in SECONDS. Again, waiting is the shorter task.
        */

        val alotted = timeOut * 1000

        val myJob = launch {
            _delayingTask()
        }
        _waitingTask()

        delay(alotted.toLong())

        myJob.cancelAndJoin()

        // I honestly have no idea if this is gonna work, so gonna need a lot of debugging.

    }

}