package Model;

import java.util.ArrayList;

public class ReferralManager {
    private static ReferralManager instance;
    private ArrayList<Referral> referrals;

    private ReferralManager() {
        referrals = new ArrayList<Referral>();
    }

    public static ReferralManager getInstance() {
        if (instance == null) {
            instance = new ReferralManager();
        }
        return instance;
    }

    public void addReferral(Referral r) {
        referrals.add(r);
    }

    public ArrayList<Referral> getAllReferrals() {
        return referrals;
    }
}