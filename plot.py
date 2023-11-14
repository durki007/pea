import numpy as np
import matplotlib.pyplot as plt
import scipy.optimize as opt
import scienceplots
import sys

# Read input file form argument
filename = sys.argv[1]

# Load data from csv file
data = np.genfromtxt(filename, delimiter=';', skip_header=1)

# Create and show plot
# fig, ax = plt.subplots()
# ax.plot(data[:, 0], data[:, 1], label="Czas wykonania [ms]")
# # Show
# ax.legend()
# plt.show()

with plt.style.context(['science', 'grid', 'scatter']):
    fig, ax = plt.subplots()
    ax.plot(data[:, 0], data[:, 1])
    ax.set_xlabel("Rozmiar problemu [n]")
    ax.set_ylabel("Czas wykonania [ms]")
    ax.set_xticks(data[:, 0])
    ax.autoscale(tight=True)
    fig.savefig("plot.jpg", dpi=300)
    plt.close()
