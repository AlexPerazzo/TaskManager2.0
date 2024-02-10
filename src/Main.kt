
// Task Manager Plans:
// Create a Task
// Mark Task as Complete
// Check Completed Tasks
// To-do date and/or completion date
// repeating task
// log how you did it?
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.io.File

fun main() {
    println("Welcome to Your Task Manager")
    var taskList = mutableListOf<String?>()
    var completedTasks = mutableListOf<String?>()
    var userChoice = 0

    while (userChoice != 8) {
        displayOptions()
        println("What would you like to do?")
        userChoice = readLine()!!.toInt()
        processChoice(userChoice, taskList, completedTasks)
    }
}


fun displayOptions() {
    // Clean up main function by having the things to display simply be in a function.
    println()
    println("Please type the number of the action you would like to do.")
    println("1. Input a Task")
    println("2. Mark a Task Complete")
    println("3. View all Tasks")
    println("4. Show Completed Tasks")
    println("5. Delete a Task")
    println("6. Save Tasks")
    println("7. Load Tasks")
    println("8. Quit Program")
    println()
}


fun processChoice(userChoice: Int, taskList: MutableList<String?>, completedTasks: MutableList<String?>) {
    // function that does most of the work
    // takes input and calls the various other functions needed to complete the task at hand
    if (userChoice == 8) {
        return
    }

    else if (userChoice == 1) {
        inputTask(taskList)
    }

    else if (userChoice == 2) {
        markTaskComplete(taskList, completedTasks)
    }

    else if (userChoice == 3) {
        if (taskList.size == 0) {
            println("You currently have no tasks.")
        }
        else {
            println("Your tasks are:")
            displayAllTasks(taskList)
        }
    }

    else if (userChoice == 4) {
        if (completedTasks.size == 0) {
            println("You currently have no completed tasks.")
        }
        else {
            println("Your completed tasks are:")
            displayAllTasks(completedTasks)
        }
    }

    else if (userChoice == 5) {
        deleteTask(taskList)

    }

    else if (userChoice == 6) {
        saveTasks(taskList, completedTasks)
    }

    else if (userChoice == 7) {
        loadTasks(taskList, completedTasks)
        }

    else {
        println("Invalid input, please try again")
        return
    }

}




fun saveTasks(taskList: MutableList<String?>, completedTasks: MutableList<String?>) {
    // saves each task on a line in a txt file in order to be loaded in later
    fun saveCompletedTasks(file: File, completedTasks: MutableList<String?>) {
        // Helper function that does the same thing but for completed tasks
        // Clear the file
        val otherfile = File("Completed_$file")
        otherfile.writeText("")
        // Loop through different tasks in the list and save it to the file
        completedTasks.forEach {task ->
            otherfile.appendText("$task")
        }
    }

    println("What file do you want to save to?")
    println("Make sure to include .txt at the end.")

    val file = File(readLine())
    // Clear the file
    // val file = File("SavedTasks.txt")
    file.writeText("")
    // Loop through different tasks in the list and save it to the file
    taskList.forEach {task ->
        file.appendText("$task\n")
    }
    saveCompletedTasks(file, completedTasks)

    println("Save complete.")
}

fun loadTasks(taskList: MutableList<String?>, completedTasks: MutableList<String?>) {
    // loads each line of a txt into the taskList list
    fun loadCompletedTasks(file: File, completedTasks: MutableList<String?>) {
        // helper function that does the same thing for the completed tasks
        val file = File("Completed_$file")
        file.bufferedReader().useLines { lines ->
            // Iterate through each line
            lines.forEach { line ->
                completedTasks.add(line)
            }
        }
    }

    println("What file do you want to load from?")
    println("Make sure to include .txt at the end.")
    val file = File(readLine())
//        val file = File("SavedTasks.txt")
    file.bufferedReader().useLines { lines ->
        // Iterate through each line
        lines.forEach { line ->
            taskList.add(line)
        }
    }
    loadCompletedTasks(file, completedTasks)
    println("Loading complete")
}


fun displayAllTasks(taskList: MutableList<String?>) {
    // prints all items in a given lists with their index + 1 in front of them
    taskList.forEachIndexed {i, task ->
        val index = i + 1
        println("$index. $task")
    }

}


fun inputTask(taskList: MutableList<String?>) {
    // Asks for input from user and updates taskList
    println("What task would you like to input?")
    val userTaskInput = readLine()
    taskList.add(userTaskInput)
    println("$userTaskInput inputted.")
}


fun markTaskComplete(taskList: MutableList<String?>, completedTasks: MutableList<String?>) {
    // Asks what task was completed. Gets it out of the taskList and adds it to completedTasks
    println("Which task would you like to complete?")
    displayAllTasks(taskList)
    println()
    println("(Input the number of the task)")
    val indexToComplete = readLine()!!.toInt()
    var taskToComplete = taskList[indexToComplete-1]
    // Find task based off index
    if (taskList.contains(taskToComplete)) {
        taskList.remove(taskToComplete)
        completedTasks.add(taskToComplete)
        println("Task completed.")
    }
    else {
        println("Input error, please try again.")
    }


}


fun deleteTask(taskList: MutableList<String?>) {
    // Asks what task should be deleted. Gets it out of the taskList
    println("Which task would you like to delete?")
    displayAllTasks(taskList)
    println()
    val indexToDelete = readLine()!!.toInt()
    var taskToDelete = taskList[indexToDelete-1]
    taskList.remove(taskToDelete)
    if (taskList.contains(taskToDelete)) {
        println("Task deleted.")
    }
    else {
        println("Input error, please try again.")
    }

}