using Microsoft.Extensions.Localization;
using TaskMasterWeb.Client.Localization;

namespace TaskMasterWeb.Client.Utilities;

public static class LocalizationUtilities
{
    public static string LocalizeStatus(string status, IStringLocalizer<Resource> localizer)
    {
        return status switch
        {
            "Backlog" => localizer["backlog"],
            "InProgress" => localizer["in-progress"],
            "Completed" => localizer["completed"],
            _ => string.Empty
        };
    }

    public static string LocalizePriority(string priority, IStringLocalizer<Resource> localizer)
    {
        return priority switch
        {
            "Low" => localizer["low"],
            "Medium" => localizer["medium"],
            "High" => localizer["high"],
            _ => string.Empty
        };
    }
}