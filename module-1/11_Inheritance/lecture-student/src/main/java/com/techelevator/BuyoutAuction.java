package com.techelevator;

public class BuyoutAuction extends Auction {
    private int buyoutPrice;

    public BuyoutAuction(String item, int buyoutPrice) {
        super(item);
        this.buyoutPrice = buyoutPrice;
    }

    @Override
    public boolean placeBid(Bid theBid) {
        if (theBid.getBidAmount() < this.buyoutPrice) {
            // call method in super class
            return super.placeBid(theBid);
        } else {
            // re-construct the bid to match the buyout price
            Bid buyoutBid = new Bid(theBid.getBidder(), buyoutPrice);
            return super.placeBid(buyoutBid);
        }

    }

    public int getBuyoutPrice() {
        return buyoutPrice;
    }

}
