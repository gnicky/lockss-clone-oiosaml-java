﻿using System.ServiceModel;
using System.Threading;
using EchoWebserviceprovider.Interfaces;
using Microsoft.IdentityModel.Claims;
using OIOSaml.Serviceprovider.Headers;
using System;
using UniqueId=System.Xml.UniqueId;

namespace EchoWebserviceProvider
{
    public class EchoService : IEchoService
    {
        public echoResponse Echo(echo echoRequest)
        {
            ValidateLibertyFrameworkHeader(echoRequest.Framework);

            Structure businessResponse = ProcessBusinessLogic(echoRequest.structureToEcho);

            echoResponse echoReply = BuildResponseMessage(businessResponse);

            InsertWsAddressingMessageIdOnResponse();

            return echoReply;
        }

        private void ValidateLibertyFrameworkHeader(LibertyFrameworkHeader framework)
        {
            if (framework == null)
            {
                throw new FaultException<FrameworkFault>(null, new FaultReason("Missing frameworkheader"), new FaultCode("FrameworkVersionMismatch", "urn:liberty:sb:2006-08"));
            }
            if (framework.Profile != "urn:liberty:sb:profile:basic")
            {
                throw new FaultException<FrameworkFault>(null, new FaultReason("Wrong profile"), new FaultCode("FrameworkVersionMismatch", "urn:liberty:sb:2006-08"));
            }
        }

        private Structure ProcessBusinessLogic(Structure structureToEcho)
        {
            var str = new Structure();
            if (structureToEcho != null)
                str.value = structureToEcho.value;
            else
            {
                str.value = "null";
            }
            return str;
        }

        private echoResponse BuildResponseMessage(Structure businessResponse)
        {
            var echoReply = new echoResponse();
            echoReply.structureToEcho = businessResponse;
            echoReply.Framework = new LibertyFrameworkHeader();
            return echoReply;
        }

        private void InsertWsAddressingMessageIdOnResponse()
        {
            OperationContext.Current.OutgoingMessageHeaders.MessageId = new UniqueId(Guid.NewGuid());
        }
    }
}
