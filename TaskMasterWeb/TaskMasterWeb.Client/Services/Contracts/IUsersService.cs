using TaskMasterWeb.Client.Dtos.User;

namespace TaskMasterWeb.Client.Services.Contracts;

public interface IUsersService
{
    Task LoginAsync(LoginDto loginDto);

    Task LogoutAsync();
    
    Task<IEnumerable<UserDto>> GetUsersAsync();
    
    Task CreateUserAsync(UserCreateDto userCreateDto);

    Task UpdateUserAsync(int id, UserUpdateDto userUpdateDto);

    Task DeleteUserAsync(int id);
}