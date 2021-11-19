package com.techelevator.tenmo.model.transfer;

import java.security.Principal;

public class TransferAuthorization {
	
	private final Principal principal;
	private final Transfer transfer;

	public TransferAuthorization(Principal principal, Transfer transfer) {
		this.principal = principal;
		this.transfer = transfer;
	}
	
	public boolean isAllowedToView() {
		return principalUsername().equals(fromUsername()) ||
				principalUsername().equals(toUsername());
	}
	
	public boolean isAllowedToCreate() {
		boolean isAllowed = false;
		if(transfer.isRequestType()) {
			isAllowed = principalUsername().equals(toUsername());
		} else if(transfer.isSendType()) {
			isAllowed = principalUsername().equals(fromUsername());
		}
		return isAllowed;
	}
	
	public boolean isAllowedToApproveOrReject() {
		return principalUsername().equals(fromUsername());
	}

	private String toUsername() {
		return transfer.getUserTo().getUsername();
	}

	private String fromUsername() {
		return transfer.getUserFrom().getUsername();
	}

	private String principalUsername() {
		return principal.getName();
	}
}
