package co.adarsh.mobile_ui.browse

/**
 * Interface to listen to book mark button events
 */
interface ProjectListener {

    fun onBookmarkedProjectClicked(projectId: String)

    fun onProjectClicked(projectId: String)

}