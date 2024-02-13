using System.Net.Http.Json;
using TaskMasterWeb.Client.Dtos.Report;
using TaskMasterWeb.Client.Services.Contracts;

namespace TaskMasterWeb.Client.Services;

public class ReportsService : IReportsService
{
    private readonly HttpClient _httpClient;

    public ReportsService(HttpClient httpClient)
    {
        _httpClient = httpClient;
    }

    public async Task<IEnumerable<ReportDto>> GetReportsAsync()
    {
        HttpResponseMessage httpResponseMessage = await _httpClient.GetAsync("api/reports");

        if (!httpResponseMessage.IsSuccessStatusCode)
        {
            throw new Exception();
        }

        IEnumerable<ReportDto>? reportDtos = await httpResponseMessage.Content.ReadFromJsonAsync<IEnumerable<ReportDto>>();

        return reportDtos!;
    }

    public async Task<ReportDto> CreateReportAsync()
    {
        HttpResponseMessage httpResponseMessage = await _httpClient.GetAsync("api/reports/generate");

        if (!httpResponseMessage.IsSuccessStatusCode)
        {
            throw new Exception();
        }

        ReportDto? reportDto = await httpResponseMessage.Content.ReadFromJsonAsync<ReportDto>();

        return reportDto!;
    }

    public async Task DeleteReportAsync(int id)
    {
        HttpResponseMessage httpResponseMessage = await _httpClient.DeleteAsync($"api/reports/{id}");

        if (!httpResponseMessage.IsSuccessStatusCode)
        {
            throw new Exception();
        }
    }
}