import numpy as np
import matplotlib.pyplot as plt
import scipy.optimize as opt
import scienceplots
import sys

# Read input file form argument
filename = sys.argv[1]
filename2 = sys.argv[2]

# Load data from csv file
data = np.genfromtxt(filename, delimiter=';', skip_header=1)
data2 = np.genfromtxt(filename2, delimiter=';', skip_header=1)

# Create and show plot
# fig, ax = plt.subplots()
# ax.plot(data[:, 0], data[:, 1], label="Czas wykonania [ms]")
# # Show
# ax.legend()
# plt.show()

with plt.style.context(['science', 'grid']):
    fig, ax = plt.subplots(figsize=(8, 4))
    ax.plot(data[:, 0], data[:, 1], label="Brute Force")
    ax.plot(data2[:, 0], data2[:, 1], label="Dynamic Programming")
    ax.legend(title="Metoda", loc="upper left")
    ax.set_xlabel("Rozmiar problemu [n]")
    ax.set_ylabel("Czas wykonania [ms]")
    ax.set_xticks(data2[:, 0])
    ax.autoscale(tight=True)
    fig.savefig("plot.jpg", dpi=300)
    plt.close()
