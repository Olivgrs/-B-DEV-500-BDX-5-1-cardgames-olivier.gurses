using System.Collections.Generic;

namespace WebApi.Class
{
    public class JsonAbout
    {
        public Client client;
        public Server server;
    }
    public class Client
    {
        public string host;
    }

    public class Param
    {
        public string name;

        public string type;
    }

    public class WidgetJson
    {
        public string name;

        public string description;
    }

    public class Service
    {
        public string name;
    }

    public class Server
    {
        public string current_time;
        public List<Service> services;
        public List<WidgetJson> widgets;
        public List<Param> parameters;
    }
}