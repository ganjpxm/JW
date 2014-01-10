function parseBigInt(B, A) {
	return new BigInteger(B, A)
}
function randomBytes(C) {
	var A = [];
	var B = 0;
	for (B = 0; B < C; B++) {
		A[B] = Math.ceil(Math.random() * 255)
	}
	return A
}
function pkcs1pad2(F, A) {
	var I = Math.ceil(F.bitLength() / 8);
	if (A < I + 11 + 4) {
		alert("Message too long for RSA");
		return null
	}
	var E = [ 0, 2, 255, 255, 255, 255 ];
	var B;
	B = A - I - 7;
	var G = 0;
	var D = 6;
	while (D < B + 6) {
		G = 0;
		while (G == 0) {
			G = Math.floor(Math.random() * 255)
		}
		E[D++] = G
	}
	var H = new BigInteger(E);
	var C = H.toString(16) + "00" + F.toString(16);
	return new BigInteger(C, 16)
}
function RSAKey() {
	this.n = null;
	this.e = 0;
	this.d = null
}
RSAKey.prototype.setPublic = function(B, A) {
	if (B != null && A != null && B.length > 0 && A.length > 0) {
		this.n = parseBigInt(B, 16);
		this.e = parseInt(A, 16)
	} else {
		alert("Invalid RSA public key")
	}
};
RSAKey.prototype.doPublic = function(A) {
	return A.modPowInt(this.e, this.n)
};
RSAKey.prototype.encrypt = function(B) {
	var A = pkcs1pad2(B, (this.n.bitLength() + 7) >> 3);
	if (A == null) {
		return null
	}
	var D = this.doPublic(A);
	if (D == null) {
		return null
	}
	var C = D.toString(16);
	if ((C.length & 1) == 0) {
		return C
	} else {
		return "0" + C
	}
};