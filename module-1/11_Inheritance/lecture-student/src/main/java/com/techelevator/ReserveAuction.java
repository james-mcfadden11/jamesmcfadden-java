package com.techelevator;

public class ReserveAuction extends Auction {
    private int reservePrice;

    public ReserveAuction(String itemForSale, int reservePrice) {
        super(itemForSale);
        this.reservePrice = reservePrice;
    }

    @Override
    public boolean placeBid(Bid theBid) {
        // if the bid meets the reserve price, let it through
        // if not, don't
        if (theBid.getBidAmount() >= reservePrice) {
            return super.placeBid(theBid);
        } else {
            return false;
        }
    }

    public int getReservePrice() {
        return reservePrice;
    }
}
