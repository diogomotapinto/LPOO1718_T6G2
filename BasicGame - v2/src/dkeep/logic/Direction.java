package dkeep.logic;

public enum Direction implements DirectionVector {
	UP {
		public int[] getVector() {
			return new int[] { -1, 0 };
		}

	},
	DOWN {
		public int[] getVector() {
			return new int[] { 1, 0 };
		}

	},
	LEFT {
		public int[] getVector() {
			return new int[] { 0, -1 };
		}
	},
	RIGHT {
		public int[] getVector() {
			return new int[] { 0, +1 };
		}

	},
	INVALID {
		public int[] getVector() {
			return new int[] { 0, 0 };
		}
	};
}
