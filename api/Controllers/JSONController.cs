using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json.Linq;
using System;
using System.Net;
using System.Net.Sockets;
using WebApi.Class;

namespace WebApi.Controllers
{
    public class JSONController : Controller
    {
        [HttpGet("about.json")]
        public ContentResult About()
        {
            JsonAbout jsonAbout = new JsonAbout();
            jsonAbout.client = new Client();
            jsonAbout.server = new Server();

            Service service = new Service();
            Service service1 = new Service();
            Service service2 = new Service();

            WidgetJson widget = new WidgetJson();
            WidgetJson widget1 = new WidgetJson();
            WidgetJson widget2 = new WidgetJson();
            WidgetJson widget3 = new WidgetJson();
            WidgetJson widget4 = new WidgetJson();
            WidgetJson widget5 = new WidgetJson();
            WidgetJson widget6 = new WidgetJson();
            WidgetJson widget7 = new WidgetJson();
            WidgetJson widget8 = new WidgetJson();
            WidgetJson widget9 = new WidgetJson();
            WidgetJson widget10 = new WidgetJson();
            WidgetJson widget11 = new WidgetJson();
            WidgetJson widget12 = new WidgetJson();
            WidgetJson widget13 = new WidgetJson();
            WidgetJson widget14 = new WidgetJson();
            WidgetJson widget15 = new WidgetJson();
            WidgetJson widget16 = new WidgetJson();
            WidgetJson widget17 = new WidgetJson();
            WidgetJson widget18 = new WidgetJson();

            Param param = new Param();
            Param param1 = new Param();
            Param param2 = new Param();
            Param param3 = new Param();
            Param param4 = new Param();
            Param param5 = new Param();
            Param param6 = new Param();
            Param param7 = new Param();
            Param param8 = new Param();
            Param param9 = new Param();
            Param param10 = new Param();
            Param param11 = new Param();
            Param param12 = new Param();
            Param param13 = new Param();
            Param param14 = new Param();
            Param param15 = new Param();
            Param param16 = new Param();
            Param param17 = new Param();
            Param param18 = new Param();
            Param param19 = new Param();
            Param param20 = new Param();
            Param param21 = new Param();
            Param param22 = new Param();
            Param param23 = new Param();
            Param param24 = new Param();
            Param param25 = new Param();
            Param param26 = new Param();
            Param param27 = new Param();
            Param param28 = new Param();
            Param param29 = new Param();
            Param param30 = new Param();
            Param param31 = new Param();

            jsonAbout.client.host = GetLocalIPAddress();
            jsonAbout.server.current_time = DateTimeOffset.UtcNow.ToUnixTimeSeconds().ToString();

            service.name = "facebook";
            service1.name = "paypal";
            service2.name = "imgur";

            //Facebook
            widget.name = "friends list";

            //paypal
            widget1.name = "payment widget";

            //imgur
            widget2.name = "feedImages";
            widget3.name = "favoImages";
            //weather
            widget4.name = "openWeather";
            //news
            widget5.name = "searchNews";
            widget6.name = "articles";
            widget7.name = "newslist";
            //covid
            widget8.name = "Global States";
            widget9.name = "SearchbyCountry";
            //movie
            widget10.name = "UpComing";
            widget11.name = "Most Popular";
            widget12.name = "Now Playing";
            widget13.name = "Top Rated";
            //serie
            widget14.name = "TV Airing Today";
            widget15.name = "TV On The Air";
            widget16.name = "TV Most Popular";
            widget17.name = "TV Top Rated";

            //widget18.name18 = "";

            //facebook
            widget.description = "see your friends list(only the friends who allowed you to see their profil)";
            //paypal
            widget1.description = "Pay a coffee with your account";
            //imgur
            widget2.description = "See the Image from the feed";
            widget3.description = "See the Image you have in you favorite";
            //weather
            widget4.description = "Search the city you want to see the weather";
            //news
            widget5.description = "Search the news you want";
            widget6.description = "See the articles";
            widget7.description = "List of the news which have been posted sorted by time of publication";
            //covid
            widget8.description = "See the situation all around the world about the situation of the covid";
            widget9.description = "Search the country you want to see the situation about the covid";
            //movie
            widget10.description = "See the UpComing film and their details (image, name, rate, description)";
            widget11.description = "See the most Popular film and their details (image, name, rate, description)";
            widget12.description = "See the Now playing film and their details (image, name, rate, description)";
            widget13.description = "See the Top Rated film and their details (image, name, rate, description)";
            //serie
            widget14.description = "See TV Airing Today and their details (image, name, rate, description)";
            widget15.description = "See TV On The Air and their details (image, name, rate, description)";
            widget16.description = "See TV Most Popular and their details (image, name, rate, description)";
            widget17.description = "See TV Top Rated and their details (image, name, rate, description)";
            
            //widget18.description18 = "";

            //facebook
            param.name = "friendlist";
            param1.name = "auth";
            param2.name = "photo";
            //paypal
            param3.name = "acount";
            param4.name = "paymentID";
            param5.name = "payementAmount";
            param6.name = "payementStatus";
            //imgur
            param7.name = "favory";
            param8.name = "feed";
            //weather
            param9.name = "city";
            //news
            param10.name = "articleSource";
            param11.name = "articleAuthor";
            param12.name = "articleTitle";
            param13.name = "articleDescription";
            param14.name = "articleView";
            param15.name = "articleFeed";
            param16.name = "articleTime";
            param17.name = "articleName";
            param18.name = "articleID";
            //covid
            param19.name = "infosCovid";
            param20.name = "infosPerCountries";
            param21.name = "flags";
            //movie
            param22.name = "film";
            param23.name = "poster";
            param24.name = "descrption";
            param25.name = "voteAverage";
            param26.name = "trieFilm";
            //serie
            param27.name = "series";
            param28.name = "poster";
            param29.name = "descrption";
            param30.name = "voteAverage";
            param31.name = "trieSeries";
            
            //facebook
            param.type = "string";
            param1.type = "string";
            param2.type = "string";
            //paypal
            param3.type = "string";
            param4.type = "string";
            param5.type = "string";
            param6.type = "string";
            //imgur
            param7.type = "string";
            param8.type = "string";
            //weather
            param9.type = "string";
            //news
            param10.type = "string";
            param11.type = "string";
            param12.type = "string";
            param13.type = "string";
            param14.type = "string";
            param15.type = "string";
            param16.type = "string";
            param17.type = "string";
            param18.type = "string";
            //covid
            param19.type = "string";
            param20.type = "string";
            param21.type = "string";
            //movie
            param22.type = "string";
            param23.type = "string";
            param24.type = "string";
            param25.type = "string";
            param26.type = "string";
            //serie
            param27.type = "string";
            param28.type = "string";
            param29.type = "string";
            param30.type = "string";
            param31.type = "string";


            var paramFB = new JArray
            {
                new JObject {{ "name", param.name }, { "type", param.type } },
                new JObject {{ "name", param1.name }, { "type", param1.type } },
                new JObject {{ "name", param2.name }, { "type", param2.type } },
            };
            var paramPP = new JArray
            {
                new JObject {{ "name", param3.name }, { "type", param3.type } },
                new JObject {{ "name", param4.name }, { "type", param4.type } },
                new JObject {{ "name", param5.name }, { "type", param5.type } },
                new JObject {{ "name", param6.name }, { "type", param6.type } },
            };
            var paramImgfeed = new JArray
            {
                new JObject {{ "name", param8.name }, { "type", param8.type } },
            };
            var paramImgfav = new JArray
            {
                new JObject {{ "name", param7.name }, { "type", param7.type } },
            };
            var widgetsFB = new JArray
            {
                new JObject {{ "name", widget.name }, { "description", widget.description }, { "params", paramFB } },
            };
            var widgetsPP = new JArray
            {
                new JObject {{ "name", widget1.name }, { "description", widget1.description }, { "params", paramPP } },
            };
            var widgetsImg = new JArray
            {
                new JObject {{ "name", widget2.name }, { "description", widget2.description }, { "params", paramImgfeed }},
                new JObject {{ "name", widget3.name }, { "description", widget3.description }, { "params", paramImgfav }},
            };

            var servicesja = new JArray
            {
                new JObject {{ "name", service.name }, { "widgets", widgetsFB } },
                new JObject {{ "name", service1.name }, { "widgets", widgetsPP } },
                new JObject {{ "name", service2.name }, { "widgets", widgetsImg } },
            };

            var paramWeather = new JArray
            {
                new JObject {{ "name", param9.name }, { "type", param9.type } },
            };
            var paramNews = new JArray
            {
                new JObject {{ "name", param10.name }, { "type", param10.type } },
                new JObject {{ "name", param11.name }, { "type", param11.type } },
                new JObject {{ "name", param12.name }, { "type", param12.type } },
                new JObject {{ "name", param13.name }, { "type", param13.type } },
                new JObject {{ "name", param14.name }, { "type", param14.type } },
                new JObject {{ "name", param15.name }, { "type", param15.type } },
                new JObject {{ "name", param16.name }, { "type", param16.type } },
                new JObject {{ "name", param17.name }, { "type", param17.type } },
                new JObject {{ "name", param18.name }, { "type", param18.type } },
            };
            var paramCovid = new JArray
            {
                new JObject {{ "name", param19.name }, { "type", param19.type } },
                new JObject {{ "name", param20.name }, { "type", param20.type } },
                new JObject {{ "name", param21.name }, { "type", param21.type } },
            };

            var paramMovie = new JArray
            {
                new JObject {{ "name", param22.name }, { "type", param22.type } },
                new JObject {{ "name", param23.name }, { "type", param23.type } },
                new JObject {{ "name", param24.name }, { "type", param24.type } },
                new JObject {{ "name", param25.name }, { "type", param25.type } },
                new JObject {{ "name", param26.name }, { "type", param26.type } },
            };
            var paramSerie = new JArray
            {
                new JObject {{ "name", param27.name }, { "type", param27.type } },
                new JObject {{ "name", param28.name }, { "type", param28.type } },
                new JObject {{ "name", param29.name }, { "type", param29.type } },
                new JObject {{ "name", param30.name }, { "type", param30.type } },
                new JObject {{ "name", param31.name }, { "type", param31.type } },
            };
            var widgetsja = new JArray
            {
                new JObject {{ "name", widget4.name }, { "description", widget4.description }, { "params", paramWeather } },
                new JObject {{ "name", widget5.name }, { "description", widget5.description }, { "params", paramNews } },
                new JObject {{ "name", widget6.name }, { "description", widget6.description }, { "params", paramNews } },
                new JObject {{ "name", widget7.name }, { "description", widget7.description }, { "params", paramNews } },
                new JObject {{ "name", widget8.name }, { "description", widget8.description }, { "params", paramCovid } },
                new JObject {{ "name", widget9.name }, { "description", widget9.description }, { "params", paramCovid } },
                new JObject {{ "name", widget10.name }, { "description", widget10.description }, { "params", paramMovie } },
                new JObject {{ "name", widget11.name }, { "description", widget11.description }, { "params", paramMovie } },
                new JObject {{ "name", widget12.name }, { "description", widget12.description }, { "params", paramMovie } },
                new JObject {{ "name", widget13.name }, { "description", widget13.description }, { "params", paramMovie } },
                new JObject {{ "name", widget14.name }, { "description", widget14.description }, { "params", paramSerie } },
                new JObject {{ "name", widget15.name }, { "description", widget15.description }, { "params", paramSerie } },
                new JObject {{ "name", widget16.name }, { "description", widget16.description }, { "params", paramSerie } },
                new JObject {{ "name", widget17.name }, { "description", widget17.description }, { "params", paramSerie } },
            };


            var obj = new JObject();
            var client = new JObject { { "host", jsonAbout.client.host } };
            var server = new JObject() { { "current_time", jsonAbout.server.current_time }, {"services", servicesja }, {"widgets", widgetsja } };

            obj.Add("client", client);
            obj.Add("server", server);

            return Content(obj.ToString(), "application/json");
        }

        public static string GetLocalIPAddress()
        {
            var host = Dns.GetHostEntry(Dns.GetHostName());
            foreach (var ip in host.AddressList)
            {
                if (ip.AddressFamily == AddressFamily.InterNetwork)
                {
                    return ip.ToString();
                }
            }
            throw new Exception("No network adapters with an IPv4 address in the system");
        }
    }
}
