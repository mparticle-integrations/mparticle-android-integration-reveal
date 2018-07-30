package com.mparticle.kits;

import android.app.Application;
import android.content.Context;

import com.mparticle.MParticle;
import com.mparticle.kits.core.ReportingMessage;
import com.stepleaderdigital.reveal.Reveal;

import java.util.List;
import java.util.Map;

public class RevealMobileKit extends AbstractKitIntegration {

    @Override
    public List<ReportingMessage> onKitCreate(Map<String, String> settings, Context context) {
        Reveal revealSDK = Reveal.getInstance();
        String apiKey = settings.get("apiKey");
        String endpointBase = settings.get( "sdk_endpoint" );
        
        if (MParticle.getInstance().getEnvironment().equals(MParticle.Environment.Development)) {
            revealSDK.setDebug(true);
        }

        if (apiKey != null) {
            revealSDK.setAPIKey(apiKey);
            revealSDK.setServiceType(Reveal.ServiceType.PRODUCTION);
            if (endpointBase != null)
                revealSDK.setAPIEndpointBase(endpointBase);
        } else {
            throw new IllegalArgumentException("No API Key provided");
        }
        revealSDK.start((Application)context.getApplicationContext());
        return null;
    }

    @Override
    public String getName() {
        return "Reveal Mobile";
    }

    @Override
    public List<ReportingMessage> setOptOut(boolean optedOut) {
        return null;
    }
}
