/*
 * This package includes code written by Tom Wu.
 *
 * Copyright (c) 2003-2005  Tom Wu
 * All Rights Reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 */

function getByteArray(B) {
	a = new Array();
	for ( var A = 0; A < B.length; A++) {
		a[A] = B.charCodeAt(A)
	}
	return a
}
var dbits;
var j_lm = (((244837814094590) & 16777215) == 15715070);
function BigInteger(B, A, C) {
	if (B != null) {
		if ("number" == typeof B) {
			this.fromNumber(B, A, C)
		} else {
			if (A == null && "string" != typeof B) {
				this.fromString(B, 256)
			} else {
				this.fromString(B, A)
			}
		}
	}
}
function nbi() {
	return new BigInteger(null)
}
function am1(E, A, B, D, G, F) {
	while (--F >= 0) {
		var C = A * this[E++] + B[D] + G;
		G = Math.floor(C / 67108864);
		B[D++] = C & 67108863
	}
	return G
}
function am2(E, J, K, D, H, A) {
	var G = J & 32767, I = J >> 15;
	while (--A >= 0) {
		var C = this[E] & 32767;
		var F = this[E++] >> 15;
		var B = I * C + F * G;
		C = G * C + ((B & 32767) << 15) + K[D] + (H & 1073741823);
		H = (C >>> 30) + (B >>> 15) + I * F + (H >>> 30);
		K[D++] = C & 1073741823
	}
	return H
}
function am3(E, J, K, D, H, A) {
	var G = J & 16383, I = J >> 14;
	while (--A >= 0) {
		var C = this[E] & 16383;
		var F = this[E++] >> 14;
		var B = I * C + F * G;
		C = G * C + ((B & 16383) << 14) + K[D] + H;
		H = (C >> 28) + (B >> 14) + I * F;
		K[D++] = C & 268435455
	}
	return H
}
if (navigator.appName == "Nokia") {
	BigInteger.prototype.am = am3;
	dbits = 28
} else {
	if (j_lm && (navigator.appName == "Microsoft Internet Explorer")) {
		BigInteger.prototype.am = am2;
		dbits = 30
	} else {
		if (j_lm && (navigator.appName != "Netscape")) {
			BigInteger.prototype.am = am1;
			dbits = 26
		} else {
			if ((4294967295 == -1) && (navigator.appName == "Netscape")) {
				BigInteger.prototype.am = am1;
				dbits = 26
			} else {
				BigInteger.prototype.am = am3;
				dbits = 28
			}
		}
	}
}
BigInteger.prototype.DB = dbits;
BigInteger.prototype.DM = ((1 << dbits) - 1);
BigInteger.prototype.DV = (1 << dbits);
var BI_FP = 52;
BigInteger.prototype.FV = Math.pow(2, BI_FP);
BigInteger.prototype.F1 = BI_FP - dbits;
BigInteger.prototype.F2 = 2 * dbits - BI_FP;
var BI_RM = "0123456789abcdefghijklmnopqrstuvwxyz";
var BI_RC = new Array();
var rr, vv;
rr = "0".charCodeAt(0);
for (vv = 0; vv <= 9; ++vv) {
	BI_RC[rr++] = vv
}
rr = "a".charCodeAt(0);
for (vv = 10; vv < 36; ++vv) {
	BI_RC[rr++] = vv
}
rr = "A".charCodeAt(0);
for (vv = 10; vv < 36; ++vv) {
	BI_RC[rr++] = vv
}
function int2char(A) {
	return BI_RM.charAt(A)
}
function intAt(B, A) {
	var C = BI_RC[B.charCodeAt(A)];
	return (C == null) ? -1 : C
}
function bnpCopyTo(B) {
	for ( var A = this.t - 1; A >= 0; --A) {
		B[A] = this[A]
	}
	B.t = this.t;
	B.s = this.s
}
function bnpFromInt(A) {
	this.t = 1;
	this.s = (A < 0) ? -1 : 0;
	if (A > 0) {
		this[0] = A
	} else {
		if (A < -1) {
			this[0] = A + DV
		} else {
			this.t = 0
		}
	}
}
function nbv(A) {
	var B = nbi();
	B.fromInt(A);
	return B
}
function bnpFromString(G, B) {
	var D;
	if (B == 16) {
		D = 4
	} else {
		if (B == 8) {
			D = 3
		} else {
			if (B == 256) {
				D = 8
			} else {
				if (B == 2) {
					D = 1
				} else {
					if (B == 32) {
						D = 5
					} else {
						if (B == 4) {
							D = 2
						} else {
							this.fromRadix(G, B);
							return
						}
					}
				}
			}
		}
	}
	this.t = 0;
	this.s = 0;
	var F = G.length, C = false, E = 0;
	while (--F >= 0) {
		var A = (D == 8) ? G[F] & 255 : intAt(G, F);
		if (A < 0) {
			if (G.charAt(F) == "-") {
				C = true
			}
			continue
		}
		C = false;
		if (E == 0) {
			this[this.t++] = A
		} else {
			if (E + D > this.DB) {
				this[this.t - 1] |= (A & ((1 << (this.DB - E)) - 1)) << E;
				this[this.t++] = (A >> (this.DB - E))
			} else {
				this[this.t - 1] |= A << E
			}
		}
		E += D;
		if (E >= this.DB) {
			E -= this.DB
		}
	}
	if (D == 8 && (G[0] & 128) != 0) {
		this.s = -1;
		if (E > 0) {
			this[this.t - 1] |= ((1 << (this.DB - E)) - 1) << E
		}
	}
	this.clamp();
	if (C) {
		BigInteger.ZERO.subTo(this, this)
	}
}
function bnpClamp() {
	var A = this.s & this.DM;
	while (this.t > 0 && this[this.t - 1] == A) {
		--this.t
	}
}
function bnToString(B) {
	if (this.s < 0) {
		return "-" + this.negate().toString(B)
	}
	var C;
	if (B == 16) {
		C = 4
	} else {
		if (B == 8) {
			C = 3
		} else {
			if (B == 2) {
				C = 1
			} else {
				if (B == 32) {
					C = 5
				} else {
					if (B == 4) {
						C = 2
					} else {
						return this.toRadix(B)
					}
				}
			}
		}
	}
	var E = (1 << C) - 1, H, A = false, F = "", D = this.t;
	var G = this.DB - (D * this.DB) % C;
	if (D-- > 0) {
		if (G < this.DB && (H = this[D] >> G) > 0) {
			A = true;
			F = int2char(H)
		}
		while (D >= 0) {
			if (G < C) {
				H = (this[D] & ((1 << G) - 1)) << (C - G);
				H |= this[--D] >> (G += this.DB - C)
			} else {
				H = (this[D] >> (G -= C)) & E;
				if (G <= 0) {
					G += this.DB;
					--D
				}
			}
			if (H > 0) {
				A = true
			}
			if (A) {
				F += int2char(H)
			}
		}
	}
	if (B == 16 && F.length % 2 > 0) {
		F = "0" + F
	}
	return A ? F : "0"
}
function bnNegate() {
	var A = nbi();
	BigInteger.ZERO.subTo(this, A);
	return A
}
function bnAbs() {
	return (this.s < 0) ? this.negate() : this
}
function bnCompareTo(A) {
	var C = this.s - A.s;
	if (C != 0) {
		return C
	}
	var B = this.t;
	C = B - A.t;
	if (C != 0) {
		return C
	}
	while (--B >= 0) {
		if ((C = this[B] - A[B]) != 0) {
			return C
		}
	}
	return 0
}
function nbits(A) {
	var C = 1, B;
	if ((B = A >>> 16) != 0) {
		A = B;
		C += 16
	}
	if ((B = A >> 8) != 0) {
		A = B;
		C += 8
	}
	if ((B = A >> 4) != 0) {
		A = B;
		C += 4
	}
	if ((B = A >> 2) != 0) {
		A = B;
		C += 2
	}
	if ((B = A >> 1) != 0) {
		A = B;
		C += 1
	}
	return C
}
function bnBitLength() {
	if (this.t <= 0) {
		return 0
	}
	return this.DB * (this.t - 1)
			+ nbits(this[this.t - 1] ^ (this.s & this.DM))
}
function bnpDLShiftTo(C, B) {
	var A;
	for (A = this.t - 1; A >= 0; --A) {
		B[A + C] = this[A]
	}
	for (A = C - 1; A >= 0; --A) {
		B[A] = 0
	}
	B.t = this.t + C;
	B.s = this.s
}
function bnpDRShiftTo(C, B) {
	for ( var A = C; A < this.t; ++A) {
		B[A - C] = this[A]
	}
	B.t = Math.max(this.t - C, 0);
	B.s = this.s
}
function bnpLShiftTo(H, D) {
	var B = H % this.DB;
	var A = this.DB - B;
	var F = (1 << A) - 1;
	var E = Math.floor(H / this.DB), G = (this.s << B) & this.DM, C;
	for (C = this.t - 1; C >= 0; --C) {
		D[C + E + 1] = (this[C] >> A) | G;
		G = (this[C] & F) << B
	}
	for (C = E - 1; C >= 0; --C) {
		D[C] = 0
	}
	D[E] = G;
	D.t = this.t + E + 1;
	D.s = this.s;
	D.clamp()
}
function bnpRShiftTo(G, D) {
	D.s = this.s;
	var E = Math.floor(G / this.DB);
	if (E >= this.t) {
		D.t = 0;
		return
	}
	var B = G % this.DB;
	var A = this.DB - B;
	var F = (1 << B) - 1;
	D[0] = this[E] >> B;
	for ( var C = E + 1; C < this.t; ++C) {
		D[C - E - 1] |= (this[C] & F) << A;
		D[C - E] = this[C] >> B
	}
	if (B > 0) {
		D[this.t - E - 1] |= (this.s & F) << A
	}
	D.t = this.t - E;
	D.clamp()
}
function bnpSubTo(B, D) {
	var C = 0, E = 0, A = Math.min(B.t, this.t);
	while (C < A) {
		E += this[C] - B[C];
		D[C++] = E & this.DM;
		E >>= this.DB
	}
	if (B.t < this.t) {
		E -= B.s;
		while (C < this.t) {
			E += this[C];
			D[C++] = E & this.DM;
			E >>= this.DB
		}
		E += this.s
	} else {
		E += this.s;
		while (C < B.t) {
			E -= B[C];
			D[C++] = E & this.DM;
			E >>= this.DB
		}
		E -= B.s
	}
	D.s = (E < 0) ? -1 : 0;
	if (E < -1) {
		D[C++] = this.DV + E
	} else {
		if (E > 0) {
			D[C++] = E
		}
	}
	D.t = C;
	D.clamp()
}
function bnpMultiplyTo(B, D) {
	var A = this.abs(), E = B.abs();
	var C = A.t;
	D.t = C + E.t;
	while (--C >= 0) {
		D[C] = 0
	}
	for (C = 0; C < E.t; ++C) {
		D[C + A.t] = A.am(0, E[C], D, C, 0, A.t)
	}
	D.s = 0;
	D.clamp();
	if (this.s != B.s) {
		BigInteger.ZERO.subTo(D, D)
	}
}
function bnpSquareTo(C) {
	var A = this.abs();
	var B = C.t = 2 * A.t;
	while (--B >= 0) {
		C[B] = 0
	}
	for (B = 0; B < A.t - 1; ++B) {
		var D = A.am(B, A[B], C, 2 * B, 0, 1);
		if ((C[B + A.t] += A.am(B + 1, 2 * A[B], C, 2 * B + 1, D, A.t - B - 1)) >= A.DV) {
			C[B + A.t] -= A.DV;
			C[B + A.t + 1] = 1
		}
	}
	if (C.t > 0) {
		C[C.t - 1] += A.am(B, A[B], C, 2 * B, 0, 1)
	}
	C.s = 0;
	C.clamp()
}
function bnpDivRemTo(J, G, F) {
	var P = J.abs();
	if (P.t <= 0) {
		return
	}
	var H = this.abs();
	if (H.t < P.t) {
		if (G != null) {
			G.fromInt(0)
		}
		if (F != null) {
			this.copyTo(F)
		}
		return
	}
	if (F == null) {
		F = nbi()
	}
	var D = nbi(), A = this.s, I = J.s;
	var O = this.DB - nbits(P[P.t - 1]);
	if (O > 0) {
		P.lShiftTo(O, D);
		H.lShiftTo(O, F)
	} else {
		P.copyTo(D);
		H.copyTo(F)
	}
	var L = D.t;
	var B = D[L - 1];
	if (B == 0) {
		return
	}
	var K = B * (1 << this.F1) + ((L > 1) ? D[L - 2] >> this.F2 : 0);
	var S = this.FV / K, R = (1 << this.F1) / K, Q = 1 << this.F2;
	var N = F.t, M = N - L, E = (G == null) ? nbi() : G;
	D.dlShiftTo(M, E);
	if (F.compareTo(E) >= 0) {
		F[F.t++] = 1;
		F.subTo(E, F)
	}
	BigInteger.ONE.dlShiftTo(L, E);
	E.subTo(D, D);
	while (D.t < L) {
		D[D.t++] = 0
	}
	while (--M >= 0) {
		var C = (F[--N] == B) ? this.DM : Math.floor(F[N] * S + (F[N - 1] + Q)
				* R);
		if ((F[N] += D.am(0, C, F, M, 0, L)) < C) {
			D.dlShiftTo(M, E);
			F.subTo(E, F);
			while (F[N] < --C) {
				F.subTo(E, F)
			}
		}
	}
	if (G != null) {
		F.drShiftTo(L, G);
		if (A != I) {
			BigInteger.ZERO.subTo(G, G)
		}
	}
	F.t = L;
	F.clamp();
	if (O > 0) {
		F.rShiftTo(O, F)
	}
	if (A < 0) {
		BigInteger.ZERO.subTo(F, F)
	}
}
function Classic(A) {
	this.m = A
}
function cConvert(A) {
	if (A.s < 0 || A.compareTo(this.m) >= 0) {
		return A.mod(this.m)
	} else {
		return A
	}
}
function cRevert(A) {
	return A
}
function cReduce(A) {
	A.divRemTo(this.m, null, A)
}
function cMulTo(A, C, B) {
	A.multiplyTo(C, B);
	this.reduce(B)
}
function cSqrTo(A, B) {
	A.squareTo(B);
	this.reduce(B)
}
Classic.prototype.convert = cConvert;
Classic.prototype.revert = cRevert;
Classic.prototype.reduce = cReduce;
Classic.prototype.mulTo = cMulTo;
Classic.prototype.sqrTo = cSqrTo;
function bnpInvDigit() {
	if (this.t < 1) {
		return 0
	}
	var A = this[0];
	if ((A & 1) == 0) {
		return 0
	}
	var B = A & 3;
	B = (B * (2 - (A & 15) * B)) & 15;
	B = (B * (2 - (A & 255) * B)) & 255;
	B = (B * (2 - (((A & 65535) * B) & 65535))) & 65535;
	B = (B * (2 - A * B % this.DV)) % this.DV;
	return (B > 0) ? this.DV - B : -B
}
function Montgomery(A) {
	this.m = A;
	this.mp = A.invDigit();
	this.mpl = this.mp & 32767;
	this.mph = this.mp >> 15;
	this.um = (1 << (A.DB - 15)) - 1;
	this.mt2 = 2 * A.t
}
function montConvert(A) {
	var B = nbi();
	A.abs().dlShiftTo(this.m.t, B);
	B.divRemTo(this.m, null, B);
	if (A.s < 0 && B.compareTo(BigInteger.ZERO) > 0) {
		this.m.subTo(B, B)
	}
	return B
}
function montRevert(A) {
	var B = nbi();
	A.copyTo(B);
	this.reduce(B);
	return B
}
function montReduce(A) {
	while (A.t <= this.mt2) {
		A[A.t++] = 0
	}
	for ( var C = 0; C < this.m.t; ++C) {
		var B = A[C] & 32767;
		var D = (B * this.mpl + (((B * this.mph + (A[C] >> 15) * this.mpl) & this.um) << 15))
				& A.DM;
		B = C + this.m.t;
		A[B] += this.m.am(0, D, A, C, 0, this.m.t);
		while (A[B] >= A.DV) {
			A[B] -= A.DV;
			A[++B]++
		}
	}
	A.clamp();
	A.drShiftTo(this.m.t, A);
	if (A.compareTo(this.m) >= 0) {
		A.subTo(this.m, A)
	}
}
function montSqrTo(A, B) {
	A.squareTo(B);
	this.reduce(B)
}
function montMulTo(A, C, B) {
	A.multiplyTo(C, B);
	this.reduce(B)
}
Montgomery.prototype.convert = montConvert;
Montgomery.prototype.revert = montRevert;
Montgomery.prototype.reduce = montReduce;
Montgomery.prototype.mulTo = montMulTo;
Montgomery.prototype.sqrTo = montSqrTo;
function bnpIsEven() {
	return ((this.t > 0) ? (this[0] & 1) : this.s) == 0
}
function bnpExp(F, G) {
	var E = nbi(), A = nbi(), D = G.convert(this), C = nbits(F) - 1;
	D.copyTo(E);
	while (--C >= 0) {
		G.sqrTo(E, A);
		if ((F & (1 << C)) > 0) {
			G.mulTo(A, D, E)
		} else {
			var B = E;
			E = A;
			A = B
		}
	}
	return G.revert(E)
}
function bnModPowInt(B, A) {
	var C;
	if (B < 256 || A.isEven()) {
		C = new Classic(A)
	} else {
		C = new Montgomery(A)
	}
	return this.exp(B, C)
}
function bnIntValue() {
	if (this.s < 0) {
		if (this.t == 1) {
			return this[0] - this.DV
		} else {
			if (this.t == 0) {
				return -1
			}
		}
	} else {
		if (this.t == 1) {
			return this[0]
		} else {
			if (this.t == 0) {
				return 0
			}
		}
	}
	return ((this[1] & ((1 << (32 - this.DB)) - 1)) << this.DB) | this[0]
}
function bnpChunkSize(A) {
	return Math.floor(Math.LN2 * this.DB / Math.log(A))
}
function bnSigNum() {
	if (this.s < 0) {
		return -1
	} else {
		if (this.t <= 0 || (this.t == 1 && this[0] <= 0)) {
			return 0
		} else {
			return 1
		}
	}
}
function bnpToRadix(A) {
	if (A == null) {
		A = 10
	}
	if (this.signum() == 0 || A < 2 || A > 36) {
		return "0"
	}
	var C = this.chunkSize(A);
	var B = Math.pow(A, C);
	var F = nbv(B), G = nbi(), E = nbi(), D = "";
	this.divRemTo(F, G, E);
	while (G.signum() > 0) {
		D = (B + E.intValue()).toString(A).substr(1) + D;
		G.divRemTo(F, G, E)
	}
	return E.intValue().toString(A) + D
}
function bnpBitwiseTo(B, F, D) {
	var C, E, A = Math.min(B.t, this.t);
	for (C = 0; C < A; ++C) {
		D[C] = F(this[C], B[C])
	}
	if (B.t < this.t) {
		E = B.s & this.DM;
		for (C = A; C < this.t; ++C) {
			D[C] = F(this[C], E)
		}
		D.t = this.t
	} else {
		E = this.s & this.DM;
		for (C = A; C < B.t; ++C) {
			D[C] = F(E, B[C])
		}
		D.t = B.t
	}
	D.s = F(this.s, B.s);
	D.clamp()
}
function op_xor(A, B) {
	return A ^ B
}
function bnXor(A) {
	var B = nbi();
	this.bitwiseTo(A, op_xor, B);
	return B
}
function lbit(A) {
	if (A == 0) {
		return -1
	}
	var B = 0;
	if ((A & 65535) == 0) {
		A >>= 16;
		B += 16
	}
	if ((A & 255) == 0) {
		A >>= 8;
		B += 8
	}
	if ((A & 15) == 0) {
		A >>= 4;
		B += 4
	}
	if ((A & 3) == 0) {
		A >>= 2;
		B += 2
	}
	if ((A & 1) == 0) {
		++B
	}
	return B
}
BigInteger.prototype.copyTo = bnpCopyTo;
BigInteger.prototype.fromInt = bnpFromInt;
BigInteger.prototype.fromString = bnpFromString;
BigInteger.prototype.clamp = bnpClamp;
BigInteger.prototype.dlShiftTo = bnpDLShiftTo;
BigInteger.prototype.subTo = bnpSubTo;
BigInteger.prototype.rShiftTo = bnpRShiftTo;
BigInteger.prototype.drShiftTo = bnpDRShiftTo;
BigInteger.prototype.invDigit = bnpInvDigit;
BigInteger.prototype.isEven = bnpIsEven;
BigInteger.prototype.multiplyTo = bnpMultiplyTo;
BigInteger.prototype.lShiftTo = bnpLShiftTo;
BigInteger.prototype.divRemTo = bnpDivRemTo;
BigInteger.prototype.squareTo = bnpSquareTo;
BigInteger.prototype.exp = bnpExp;
BigInteger.prototype.toRadix = bnpToRadix;
BigInteger.prototype.signum = bnSigNum;
BigInteger.prototype.chunkSize = bnpChunkSize;
BigInteger.prototype.intValue = bnIntValue;
BigInteger.prototype.bitwiseTo = bnpBitwiseTo;
BigInteger.prototype.toString = bnToString;
BigInteger.prototype.negate = bnNegate;
BigInteger.prototype.abs = bnAbs;
BigInteger.prototype.compareTo = bnCompareTo;
BigInteger.prototype.bitLength = bnBitLength;
BigInteger.prototype.modPowInt = bnModPowInt;
BigInteger.prototype.xor = bnXor;
BigInteger.ZERO = nbv(0);
BigInteger.ONE = nbv(1);