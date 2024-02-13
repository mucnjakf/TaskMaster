using TaskMasterWeb.Client.Dtos.Report;

namespace TaskMasterWeb.Client.Services.Contracts;

public interface IReportsService
{
    Task<IEnumerable<ReportDto>> GetReportsAsync();

    Task<ReportDto> CreateReportAsync();
    
    Task DeleteReportAsync(int id);
}