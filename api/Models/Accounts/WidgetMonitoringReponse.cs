using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Text.Json.Serialization;

namespace WebApi.Models.Accounts
{
    public class WidgetMonitoringReponse
    {
        public String Facebook { get; set; }
        public String Cinema { get; set; }
        public String Weather { get; set; }
        public String News { get; set; }
        public String Covid { get; set; }
        public String Paypal { get; set; }
        public String Imgur { get; set; }
    }
}
