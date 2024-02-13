using System.Net.Http.Headers;
using System.Net.Http.Json;
using Blazored.LocalStorage;
using Microsoft.AspNetCore.Components.Authorization;
using TaskMasterWeb.Client.Dtos.User;
using TaskMasterWeb.Client.Providers;
using TaskMasterWeb.Client.Services.Contracts;

namespace TaskMasterWeb.Client.Services;

public class UsersService : IUsersService
{
    private readonly HttpClient _httpClient;
    private readonly AuthenticationStateProvider _authStateProvider;
    private readonly ILocalStorageService _localStorageService;

    public UsersService(HttpClient httpClient, AuthenticationStateProvider authStateProvider, ILocalStorageService localStorageService)
    {
        _httpClient = httpClient;
        _authStateProvider = authStateProvider;
        _localStorageService = localStorageService;
    }

    public async Task LoginAsync(LoginDto loginDto)
    {
        HttpResponseMessage httpResponseMessage = await _httpClient.PostAsJsonAsync("api/users/login", loginDto);

        if (!httpResponseMessage.IsSuccessStatusCode)
        {
            throw new Exception();
        }

        AuthenticationDto? authenticationDto = await httpResponseMessage.Content.ReadFromJsonAsync<AuthenticationDto>();

        await _localStorageService.SetItemAsync("jwt", authenticationDto!.Token);

        _httpClient.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Bearer", authenticationDto.Token);

        ((AuthStateProvider)_authStateProvider).NotifyUserAuthentication(authenticationDto.Token);
    }

    public async Task LogoutAsync()
    {
        await _localStorageService.RemoveItemAsync("jwt");

        ((AuthStateProvider)_authStateProvider).NotifyUserLogout();

        _httpClient.DefaultRequestHeaders.Authorization = null;
    }

    public async Task<IEnumerable<UserDto>> GetUsersAsync()
    {
        HttpResponseMessage httpResponseMessage = await _httpClient.GetAsync("api/users");

        if (!httpResponseMessage.IsSuccessStatusCode)
        {
            throw new Exception();
        }

        IEnumerable<UserDto>? userDtos = await httpResponseMessage.Content.ReadFromJsonAsync<IEnumerable<UserDto>>();

        return userDtos!;
    }

    public async Task CreateUserAsync(UserCreateDto userCreateDto)
    {
        HttpResponseMessage httpResponseMessage = await _httpClient.PostAsJsonAsync("api/users", userCreateDto);

        if (!httpResponseMessage.IsSuccessStatusCode)
        {
            throw new Exception();
        }
    }

    public async Task UpdateUserAsync(int id, UserUpdateDto userUpdateDto)
    {
        HttpResponseMessage httpResponseMessage = await _httpClient.PutAsJsonAsync($"api/users/{id}", userUpdateDto);

        if (!httpResponseMessage.IsSuccessStatusCode)
        {
            throw new Exception();
        }
    }

    public async Task DeleteUserAsync(int id)
    {
        HttpResponseMessage httpResponseMessage = await _httpClient.DeleteAsync($"api/users/{id}");

        if (!httpResponseMessage.IsSuccessStatusCode)
        {
            throw new Exception();
        }
    }
}