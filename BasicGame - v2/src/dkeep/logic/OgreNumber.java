package dkeep.logic;

public enum OgreNumber implements OgreNumberRepresentation, FoeInfo {
	NONE {
		public int getRepresentation() {
			return -1;
		}
	},
	ONE {
		public int getRepresentation() {
			return 1;
		}
	},
	TWO {
		public int getRepresentation() {
			return 2;
		}
	},
	THREE {
		public int getRepresentation() {
			return 3;
		}
	},
	FOUR {
		public int getRepresentation() {
			return 4;
		}
	},
	FIVE {
		public int getRepresentation() {
			return 5;
		}
	};

	public static OgreNumber getOgreNumber(int rep) {
		for (OgreNumber o : OgreNumber.values()) {
			if (o.getRepresentation() == rep) {
				return o;
			}
		}
		return OgreNumber.NONE;
	}
}
