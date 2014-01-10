function Util() {
}
Util.parseBigInt = function(B, A) {
	return new BigInteger(B, A)
};
Util.randomString = function(C) {
	var A = "";
	var B = 0;
	for (B = 0; B < C; B++) {
		A = A + String.fromCharCode(Math.ceil(Math.random() * 255))
	}
	return A
};
Util.randomBytes = function(C) {
	var A = [];
	var B = 0;
	for (B = 0; B < C; B++) {
		A[B] = Math.ceil(Math.random() * 255)
	}
	return A
};
Util.toHexString = function(D) {
	var C = "";
	for ( var A = 0; A < D.length; A++) {
		var B;
		if (typeof D[A] == "number") {
			B = (D[A]).toString(16)
		} else {
			if (typeof D[A] == "string") {
				B = D.charCodeAt(A).toString(16)
			}
		}
		if (B.length == 1) {
			B = "0" + B
		}
		C += B
	}
	return C
};
Util.fromHexString = function(D) {
	D = (D.length % 2 == 0) ? D : "0" + D;
	var A = D.length / 2;
	var E = [];
	for ( var C = 0, B = 0; C < A; C++, B++) {
		var F = C * 2;
		E[B] = parseInt("0x" + D.substring(F, F + 2))
	}
	return E
};
Util.fromHexToString = function(C) {
	C = (C.length % 2 == 0) ? C : "0" + C;
	var A = C.length / 2;
	var D = "";
	for ( var B = 0; B < A; B++) {
		var E = B * 2;
		D = D + String.fromCharCode(parseInt("0x" + C.substring(E, E + 2)))
	}
	return D
};
Util.cByteArrayToNString = function(C) {
	var A = "";
	for ( var B = 0; B < C.length; B++) {
		A += String.fromCharCode(C[B])
	}
	return A
};
Util.getByteArray = function(B) {
	a = new Array();
	for ( var A = 0; A < B.length; A++) {
		a[A] = B.charCodeAt(A)
	}
	return a
};
Util.xorByteArray = function(C, B) {
	if (C.length != B.length) {
		throw "Invalid parameters."
	}
	var A = [];
	for ( var D = 0; D < C.length; D++) {
		A[D] = C[D] ^ B[D]
	}
	return A
};
Util.arrayCopy = function(F, A, E, D, C) {
	E.splice(D, C);
	for ( var B = 0; B < C; B++) {
		E.splice(D, 0, F[B]);
		D++
	}
};
Util.fillByteArray = function(B, A) {
	var C, D;
	D = B.length;
	for (C = 0; C < D; C++) {
		B[C] = A
	}
	return B
};
Util.convertAsciiArrayToHexByteArray = function(D, I, E, C) {
	var F = 2;
	var G, A, B, H;
	H = Math.floor((C + 1) / 2);
	for (A = 0, B = 0; A < H; A++) {
		if (B < (C - 1)) {
			G = (D[B] & 15) << 4;
			G = G | D[B + 1] & 15
		} else {
			G = I[A + E] & 15;
			G = G | (D[B] & 15) << 4
		}
		I[A + E] = G;
		B += F
	}
	return I
};
function convertHexArrayToString(A) {
	var F = new StringBuffer();
	var D = A.length;
	var E;
	var C;
	for ( var B = 0; B < D; B++) {
		E = (A[B] & 240) >> 4;
		C = Character.forDigit(E, HEX_RADIX);
		F.append(Character.toUpperCase(C));
		E = (A[B] & 15);
		C = Character.forDigit(E, HEX_RADIX);
		F.append(Character.toUpperCase(C))
	}
	return new String(F)
}
Util.convertStringToPackedHexByteArray = function(J, E, K) {
	var I = 1;
	var D = -1;
	var F = 0;
	var B = J.length;
	var C, A, G, L;
	var H;
	for (C = 0, A = 0; C < B; C++, A++) {
		H = J.charAt(C);
		G = H.toString(16);
		if (G == D) {
			return I
		}
		L = G << 4;
		C++;
		if (C < B) {
			H = J.charAt(C);
			G = H.toString(16);
			if (G == D) {
				return I
			}
			E[A + K] = (L | G)
		} else {
			E[A + K] = (L | 15)
		}
	}
	return F
};
Util.convertStringtoHexByteArray = function(D, A) {
	var B = D.length();
	for ( var C = 0; C < B; C++) {
		A[C] = D.charAt(C)
	}
	return A
};
Util.convertHexByteVectorToString = function(G) {
	var F = new StringBuffer();
	var E = G.size();
	var C;
	var D;
	var B;
	for ( var A = 0; A < E; A++) {
		C = G.elementAt(A);
		D = (C.intValue() & 240) >> 4;
		B = Character.forDigit(D, HEX_RADIX);
		F.append(Character.toUpperCase(B));
		D = C.intValue() & 15;
		B = Character.forDigit(D, HEX_RADIX);
		F.append(Character.toUpperCase(B))
	}
	return new String(F)
};