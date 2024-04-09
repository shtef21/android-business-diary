package com.github.shtef21.businessdiary.logic

import java.util.Date
import java.util.UUID

data class DiaryLog (
    val logId: String = UUID.randomUUID().toString(),
    var title: String = "",
    var description: String = ""
)

/*
    Title/Activity Name: This should be a concise description of the task or activity you performed during the day. Keep it brief but descriptive enough to understand at a glance.
*/
/*
    Date/Time: Include the date and time when the activity was completed. This helps in organizing and tracking your daily tasks chronologically.
*/
/*
    Description/Details: Provide more information about the task or activity. Include any relevant details, notes, or updates related to the activity. This could include progress made, challenges faced, solutions implemented, or any other relevant information.
*/
/*
    Category/Tags: Assign categories or tags to your entries for easy filtering and organization. This could include project names, client names, task types, or any other relevant categories that help you classify your activities.
*/
/*
    Priority/Status: Optionally, you can include a priority level or status indicator to prioritize tasks or track their progress. This could be as simple as "High/Medium/Low" priority or "In Progress/Done" status.
*/
/*
    Attachments/Links: If applicable, allow users to attach files, images, or links related to the activity. This could include documents, screenshots, or reference materials that provide context or support for the task.
*/
/*
    Reflection/Notes: Provide a space for users to reflect on their activities, jot down insights, lessons learned, or future actions to take based on their experiences during the day.
*/
