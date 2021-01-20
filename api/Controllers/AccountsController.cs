using AutoMapper;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using WebApi.Class;
using WebApi.Entities;
using WebApi.Models.Accounts;
using WebApi.Services;

namespace WebApi.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class AccountsController : BaseController
    {
        private readonly IAccountService _accountService;
        private readonly IMapper _mapper;

        public AccountsController(
            IAccountService accountService,
            IMapper mapper)
        {
            _accountService = accountService;
            _mapper = mapper;
        }

        [HttpPost("authenticate")]
        public ActionResult<AuthenticateResponse> Authenticate(AuthenticateRequest model)
        {
            var response = _accountService.Authenticate(model, ipAddress());
            setTokenCookie(response.RefreshToken);
            return Ok(response);
        }

        [HttpPost("ManageWidgetFacebook")]
        public IActionResult ManageWidgetFacebook(WidgetMonitoringReponse model)
        {
            if (model.Facebook == "0")
            {
                _accountService.WidgetFacebook(model.Facebook);
                
                return Ok(new { message = "Widget Facebook is deactivated" });
            }
            else
            {
                _accountService.WidgetFacebook(model.Facebook);
                return Ok(new { message = "Widget Facebook is activated" });
            }
        }
        [HttpPost("ManageWidgetCinema")]
        public IActionResult ManageWidgetCinema(WidgetMonitoringRequest model)
        {
            if (model.Cinema == "0")
            {
                _accountService.WidgetCinema(model.Cinema);
                return Ok(new { message = "Widget Cinema is deactivated" });
            }
            else
            {
                _accountService.WidgetCinema(model.Cinema);
                return Ok(new { message = "Widget Cinema is activated" });
            }
        }

        [HttpPost("ManageWidgetWeather")]
        public IActionResult ManageWidgetWeather(WidgetMonitoringRequest model)
        {
            if (model.Weather == "0")
            {
                _accountService.WidgetWeather(model.Weather);
                return Ok(new { message = "Widget Weather is deactivated" });
            }
            else
            {
                _accountService.WidgetWeather(model.Weather);
                return Ok(new { message = "Widget Weather is activated" });
            }
        }

        [HttpPost("ManageWidgetNews")]
        public IActionResult ManageWidgetNews(WidgetMonitoringRequest model)
        {
            if (model.News == "0")
            {
                _accountService.WidgetNews(model.News);
                return Ok(new { message = "Widget News is deactivated" });
            }
            else
            {
                _accountService.WidgetNews(model.News);
                return Ok(new { message = "Widget News is activated" });
            }
        }

        [HttpPost("ManageWidgetCovid")]
        public IActionResult ManageWidgetCovid(WidgetMonitoringRequest model)
        {
            if (model.Covid == "0")
            {
                _accountService.WidgetCovid(model.Covid);
                return Ok(new { message = "Widget Covid is deactivated" });
            }
            else
            {
                _accountService.WidgetCovid(model.Covid);
                return Ok(new { message = "Widget Covid is activated" });
            }
        }

        [HttpPost("ManageWidgetPaypal")]
        public IActionResult ManageWidgetpaypal(WidgetMonitoringRequest model)
        {
            if (model.Paypal == "0")
            {
                _accountService.WidgetPaypal(model.Paypal);
                return Ok(new { message = "Widget paypal is deactivated" });
            }
            else
            {
                _accountService.WidgetPaypal(model.Paypal);
                return Ok(new { message = "Widget paypal is activated" });
            }
        }


        [HttpPost("ManageWidgetImgur")]
        public IActionResult ManageWidgetImgur(WidgetMonitoringRequest model)
        {
            if (model.Imgur == "0")
            {
                _accountService.WidgetImgur(model.Imgur);
                return Ok(new { message = "Widget Imgur is deactivated" });
            }
            else
            {
                _accountService.WidgetImgur(model.Imgur);
                return Ok(new { message = "Widget Imgur is activated" });
            }
        }

        [HttpPost("refresh-token")]
        public ActionResult<AuthenticateResponse> RefreshToken()
        {
            var refreshToken = Request.Cookies["refreshToken"];
            var response = _accountService.RefreshToken(refreshToken, ipAddress());
            setTokenCookie(response.RefreshToken);
            return Ok(response);
        }

        [Authorize]
        [HttpPost("revoke-token")]
        public IActionResult RevokeToken(RevokeTokenRequest model)
        {
            var token = model.Token ?? Request.Cookies["refreshToken"];

            if (string.IsNullOrEmpty(token))
                return BadRequest(new { message = "Token is required" });

            _accountService.RevokeToken(token, ipAddress());
            return Ok(new { message = "Token revoked" });
        }

        [HttpPost("register")]
        public IActionResult Register(RegisterRequest model)
        {
            _accountService.Register(model, Request.Headers["origin"]);
            return Ok(new { message = "Registration successful, please check your email for verification instructions" });
        }

        [HttpPost("verify-email")]
        public IActionResult VerifyEmail(VerifyEmailRequest model)
        {
            _accountService.VerifyEmail(model.Token);
            return Ok(new { message = "Verification successful, you can now login" });
        }

        [HttpPost("forgot-password")]
        public IActionResult ForgotPassword(ForgotPasswordRequest model)
        {
            _accountService.ForgotPassword(model, Request.Headers["origin"]);
            return Ok(new { message = "Please check your email for password reset instructions" });
        }

        [HttpPost("validate-reset-token")]
        public IActionResult ValidateResetToken(ValidateResetTokenRequest model)
        {
            _accountService.ValidateResetToken(model);
            return Ok(new { message = "Token is valid" });
        }

        [HttpPost("reset-password")]
        public IActionResult ResetPassword(ResetPasswordRequest model)
        {
            _accountService.ResetPassword(model);
            return Ok(new { message = "Password reset successful, you can now login" });
        }

        [Authorize]
        [HttpGet("{id:int}")]
        public ActionResult<AccountResponse> GetById(int id)
        {
            var account = _accountService.GetById(id);
            return Ok(account);
        }
        [Authorize]
        [HttpPut("{id:int}")]
        public ActionResult<AccountResponse> Update(int id, UpdateRequest model)
        {
            var account = _accountService.Update(id, model);
            return Ok(account);
        }

        [Authorize]
        [HttpDelete("{id:int}")]
        public IActionResult Delete(int id)
        {
            _accountService.Delete(id);
            return Ok(new { message = "Account deleted successfully" });
        }

        private void setTokenCookie(string token)
        {
            var cookieOptions = new CookieOptions
            {
                HttpOnly = true,
                Expires = DateTime.UtcNow.AddDays(7)
            };
            Response.Cookies.Append("refreshToken", token, cookieOptions);
        }

        private string ipAddress()
        {
            if (Request.Headers.ContainsKey("X-Forwarded-For"))
                return Request.Headers["X-Forwarded-For"];
            else
                return HttpContext.Connection.RemoteIpAddress.MapToIPv4().ToString();
        }
    }
}
