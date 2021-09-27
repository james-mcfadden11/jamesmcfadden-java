package com.techelevator;

public class BuyoutAuction extends Auction {
    private int buyoutPrice;

    public BuyoutAuction(String item, int buyoutPrice) {
        super(item);
        this.buyoutPrice = buyoutPrice;
    }

    @Override
    public boolean placeBid(Bid theBid) {
        if(theBid.getBidAmount() < buyoutPrice) {
            return super.placeBid(theBid);
        } else {
            // adjust the bid to meet the buyout price exactly
            Bid buyoutBid = new Bid(theBid.getBidder(), buyoutPrice);
            return super.placeBid(buyoutBid);
        }
    }

    public int getBuyoutPrice() {
        return buyoutPrice;
    }
}
