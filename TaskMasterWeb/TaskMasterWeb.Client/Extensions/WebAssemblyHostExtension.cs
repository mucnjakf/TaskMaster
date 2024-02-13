using System.Globalization;
using Microsoft.AspNetCore.Components.WebAssembly.Hosting;
using Microsoft.JSInterop;

namespace TaskMasterWeb.Client.Extensions;

public static class WebAssemblyHostExtension
{
    public static async Task SetDefaultCultureAsync(this WebAssemblyHost host)
    {
        IJSRuntime jsInterop = host.Services.GetRequiredService<IJSRuntime>();

        string? result = await jsInterop.InvokeAsync<string>("blazorCulture.get");

        CultureInfo culture = result is not null ? new CultureInfo(result) : new CultureInfo("en-US");

        CultureInfo.DefaultThreadCurrentCulture = culture;
        CultureInfo.DefaultThreadCurrentUICulture = culture;
    }
}