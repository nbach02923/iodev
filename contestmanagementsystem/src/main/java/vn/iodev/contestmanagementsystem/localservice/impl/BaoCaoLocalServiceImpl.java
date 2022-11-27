package vn.iodev.contestmanagementsystem.localservice.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import lombok.extern.slf4j.Slf4j;
import vn.iodev.contestmanagementsystem.exportdata.DanhSachDangKyMau1;
import vn.iodev.contestmanagementsystem.model.CuocThi;
import vn.iodev.contestmanagementsystem.model.DanhSachThi;
import vn.iodev.contestmanagementsystem.model.DoanThi;
import vn.iodev.contestmanagementsystem.model.DoiThi;
import vn.iodev.contestmanagementsystem.model.HuanLuyenVien;
import vn.iodev.contestmanagementsystem.model.KhoiThi;
import vn.iodev.contestmanagementsystem.model.ThiSinh;
import vn.iodev.contestmanagementsystem.repository.CuocThiRepository;
import vn.iodev.contestmanagementsystem.repository.DanhSachThiRepository;
import vn.iodev.contestmanagementsystem.repository.DoanThiRepository;
import vn.iodev.contestmanagementsystem.repository.DoiThiRepository;
import vn.iodev.contestmanagementsystem.repository.HuanLuyenVienRepository;
import vn.iodev.contestmanagementsystem.repository.KhoiThiRepository;
import vn.iodev.contestmanagementsystem.repository.ThiSinhRepository;
import vn.iodev.contestmanagementsystem.utils.DatetimeUtil;

@Service
@Slf4j
public class BaoCaoLocalServiceImpl {
	@Autowired
	private DoanThiRepository doanThiRepository;

	@Autowired
	private CuocThiRepository cuocThiRepository;

	@Autowired
	private HuanLuyenVienRepository huanLuyenVienRepository;

	@Autowired
	private DanhSachThiRepository danhSachThiRepository;

	@Autowired
	private ThiSinhRepository thiSinhRepository;

	@Autowired
	private KhoiThiRepository khoiThiRepository;

	@Autowired
	private DoiThiRepository doiThiRepository;

	public HashMap<String, Object> getDanhSachDangKyMau1(String doanThiId) {

		HashMap<String, Object> data = new HashMap<>();

		Optional<DoanThi> doanThiOpt = doanThiRepository.findById(doanThiId);

		if (!doanThiOpt.isPresent()) {
			return null;
		}

		DoanThi doanThi = doanThiOpt.get();

		if (ObjectUtils.isEmpty(doanThi.getCuocThiId())) {
			return null;
		}

		Optional<CuocThi> cuocThiOpt = cuocThiRepository.findById(doanThi.getCuocThiId());

		if (!cuocThiOpt.isPresent()) {
			return null;
		}

		CuocThi cuocThi = cuocThiOpt.get();

		List<HuanLuyenVien> huanLuyenViens = huanLuyenVienRepository.findByCuocThiId(cuocThi.getId());

		data.put(DanhSachDangKyMau1.tenCuocThi, cuocThi.getTenGoi());

		data.put(DanhSachDangKyMau1.ngayBatDau,
				DatetimeUtil.dateToString(cuocThi.getNgayBatDau(), DatetimeUtil._VN_DATE_FORMAT));

		data.put(DanhSachDangKyMau1.ngayKetThuc,
				DatetimeUtil.dateToString(cuocThi.getNgayKetThuc(), DatetimeUtil._VN_DATE_FORMAT));

		data.put(DanhSachDangKyMau1.diaDiemToChuc, cuocThi.getDiaDiemToChuc());

		data.put(DanhSachDangKyMau1.tenDoan, doanThi.getTenGoi());

		data.put(DanhSachDangKyMau1.diaChiDoan, doanThi.getDiaChiHoatDong());

		// danhSachDangKyMau1.setDiaChiDoan(doanThi.getDiaChiHoatDong());

		if (huanLuyenViens != null) {
			for (HuanLuyenVien huanLuyenVien : huanLuyenViens) {
				if (huanLuyenVien.getTruongPhoDoan() == 1) {
					data.put(DanhSachDangKyMau1.tenTruongDoan, huanLuyenVien.getHoTen());
					data.put(DanhSachDangKyMau1.emailTruongDoan, huanLuyenVien.getEmail());
					data.put(DanhSachDangKyMau1.soDienThoaiTruongDoan, huanLuyenVien.getSoDienThoai());
				} else if (huanLuyenVien.getTruongPhoDoan() == 2) {
					data.put(DanhSachDangKyMau1.tenPhoDoan, huanLuyenVien.getHoTen());
					data.put(DanhSachDangKyMau1.emailPhoDoan, huanLuyenVien.getEmail());
					data.put(DanhSachDangKyMau1.soDienThoaiPhoDoan, huanLuyenVien.getSoDienThoai());
				}
			}
		}

		List<DanhSachThi> danhSachThis = danhSachThiRepository.findByCuocThiId(cuocThi.getId());

		List<HashMap<String, Object>> danhSachDangKy = new ArrayList<HashMap<String, Object>>();

		List<HashMap<String, Object>> danhSachDangKyOLP = new ArrayList<HashMap<String, Object>>();

		danhSachDangKyOLP.add(createDataRow(null, "OLP", null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null));

		List<HashMap<String, Object>> danhSachDangKyICPC = new ArrayList<HashMap<String, Object>>();

		danhSachDangKyICPC.add(createDataRow(null, "ICPC", null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null));

		LinkedHashMap<String, List<HashMap<String, Object>>> doiThiMap = new LinkedHashMap<>();

		int olpCount = 1;

		if (danhSachThis != null) {
			for (DanhSachThi danhSachThi : danhSachThis) {
				
				if(ObjectUtils.isEmpty(danhSachThi.getThiSinhId())) {
					log.info("thiSinhId is null at danhSachThi:{}", danhSachThi.getDanhSachThiId());
					continue;
				}
				
				Optional<ThiSinh> thiSinhOpt = thiSinhRepository.findById(danhSachThi.getThiSinhId());
				
				if (!thiSinhOpt.isPresent()) {
					continue;
				}
				
				ThiSinh thiSinh = thiSinhOpt.get();
				
				if(ObjectUtils.isEmpty(danhSachThi.getKhoiThiId())) {
					log.info("khoiThiId is null at danhSachThi:{}", danhSachThi.getDanhSachThiId());
					continue;
				}

				Optional<KhoiThi> khoiThiOpt = khoiThiRepository.findById(danhSachThi.getKhoiThiId());
				
				if(ObjectUtils.isEmpty(danhSachThi.getDoiThiId())) {
					log.info("doiThiId is null at danhSachThi:{}", danhSachThi.getDanhSachThiId());
					continue;
				}

				Optional<DoiThi> doiThiOpt = doiThiRepository.findById(danhSachThi.getDoiThiId());

				KhoiThi khoiThi = khoiThiOpt.isPresent() ? khoiThiOpt.get() : null;

				DoiThi doiThi = doiThiOpt.isPresent() ? doiThiOpt.get() : null;

				String lienHe = thiSinh.getEmail();

				if (!ObjectUtils.isEmpty(thiSinh.getSoDienThoai())) {
					lienHe += ObjectUtils.isEmpty(lienHe) ? thiSinh.getSoDienThoai()
							: (", " + thiSinh.getSoDienThoai());
				}

				String chuyenTin = "";
				String khongChuyenTin = "";
				String icpcChuyenTin = "";
				String icpcKhongChuyen = "";
				String caoDang = "";
				String procon = "";
				String pmnm = "";

				String tenKhoi = khoiThi != null ? khoiThi.getTenGoi().toLowerCase() : "";

				if (tenKhoi.contains("phần mềm nguồn mở")) {
					pmnm = "X";
				} else if (tenKhoi.contains("procon")) {
					procon = "X";
				} else if (tenKhoi.contains("cao đẳng")) {
					caoDang = "X";
				} else if (tenKhoi.contains("icpc chuyên tin")) {
					icpcChuyenTin = "X";
				} else if (tenKhoi.contains("icpc không chuyên tin")) {
					icpcKhongChuyen = "X";
				} else if (tenKhoi.contains("không chuyên tin") && !tenKhoi.contains("icpc")) {
					khongChuyenTin = "X";
				} else if (tenKhoi.contains("chuyên tin") && !tenKhoi.contains("icpc") && !tenKhoi.contains("không")) {
					chuyenTin = "X";
				}

				if (doiThi == null) {
					// add to olp

					HashMap<String, Object> dataRow = createDataRow(olpCount, thiSinh.getHoTen(),
							thiSinh.getDoiTuongThi(), lienHe, thiSinh.getNganhDaoTao(), null,
							DatetimeUtil.dateToString(thiSinh.getNgaySinh(), DatetimeUtil._VN_DATE_FORMAT),
							thiSinh.getGioiTinh() == 0 ? "X" : null, thiSinh.getGioiTinh() == 1 ? "X" : null, chuyenTin,
							khongChuyenTin, icpcChuyenTin, icpcKhongChuyen, caoDang, procon, pmnm,
							thiSinh.getDatGiaiThuong(), null);

					danhSachDangKyOLP.add(dataRow);
					olpCount++;
				} else {
					String tenDoi = doiThi.getTenGoi();
					List<HashMap<String, Object>> dataRows = new ArrayList<HashMap<String, Object>>();
					if (!doiThiMap.containsKey(tenDoi)) {
						dataRows.add(createDataRow(null, tenDoi, null, null, null, null, null, null, null, null, null,
								null, null, null, null, null, null, null));

						doiThiMap.put(tenDoi, dataRows);
					} else {
						dataRows = doiThiMap.get(tenDoi);
					}

					HashMap<String, Object> dataRow = createDataRow(dataRows.size(), thiSinh.getHoTen(),
							thiSinh.getDoiTuongThi(), lienHe, thiSinh.getNganhDaoTao(), null,
							DatetimeUtil.dateToString(thiSinh.getNgaySinh(), DatetimeUtil._VN_DATE_FORMAT),
							thiSinh.getGioiTinh() == 0 ? "X" : null, thiSinh.getGioiTinh() == 1 ? "X" : null, chuyenTin,
							khongChuyenTin, icpcChuyenTin, icpcKhongChuyen, caoDang, procon, pmnm,
							thiSinh.getDatGiaiThuong(), null);

					dataRows.add(dataRow);

					doiThiMap.put(tenDoi, dataRows);

				}
			}

			doiThiMap.forEach((k, v) -> {
				danhSachDangKyICPC.addAll(v);
			});

			danhSachDangKy.addAll(danhSachDangKyOLP);
			danhSachDangKy.addAll(danhSachDangKyICPC);

			data.put(DanhSachDangKyMau1.loop, danhSachDangKy);
		}

		return data;

	}

	private HashMap<String, Object> createDataRow(Integer thuTu, String hoTen, String namThu, String lienHe,
			String khoaDaoTao, String soMuiTiem, String ngayThangNamSinh, String gioiTinhNam, String gioiTinhNu,
			String chuyenTin, String khongChuyenTin, String icpcChuyenTin, String icpcKhongChuyen, String caoDang,
			String procon, String pmnm, String giai, Integer namThamDu) {
		HashMap<String, Object> map = new HashMap<>();

		if (!ObjectUtils.isEmpty(thuTu)) {
			map.put(DanhSachDangKyMau1.thuTu, thuTu);
		}

		if (!ObjectUtils.isEmpty(hoTen)) {
			map.put(DanhSachDangKyMau1.hoTen, hoTen);
		}
		if (!ObjectUtils.isEmpty(namThu)) {
			map.put(DanhSachDangKyMau1.namThu, namThu);
		}
		if (!ObjectUtils.isEmpty(lienHe)) {
			map.put(DanhSachDangKyMau1.lienHe, lienHe);
		}
		if (!ObjectUtils.isEmpty(khoaDaoTao)) {
			map.put(DanhSachDangKyMau1.khoaDaoTao, khoaDaoTao);
		}
		if (!ObjectUtils.isEmpty(soMuiTiem)) {
			map.put(DanhSachDangKyMau1.soMuiTiem, soMuiTiem);
		}
		if (!ObjectUtils.isEmpty(ngayThangNamSinh)) {
			map.put(DanhSachDangKyMau1.ngayThangNamSinh, ngayThangNamSinh);
		}
		if (!ObjectUtils.isEmpty(gioiTinhNam)) {
			map.put(DanhSachDangKyMau1.gioiTinhNam, gioiTinhNam);
		}
		if (!ObjectUtils.isEmpty(gioiTinhNu)) {
			map.put(DanhSachDangKyMau1.gioiTinhNu, gioiTinhNu);
		}
		if (!ObjectUtils.isEmpty(chuyenTin)) {
			map.put(DanhSachDangKyMau1.chuyenTin, chuyenTin);
		}
		if (!ObjectUtils.isEmpty(khongChuyenTin)) {
			map.put(DanhSachDangKyMau1.khongChuyenTin, khongChuyenTin);
		}
		if (!ObjectUtils.isEmpty(icpcChuyenTin)) {
			map.put(DanhSachDangKyMau1.icpcChuyenTin, icpcChuyenTin);
		}
		if (!ObjectUtils.isEmpty(icpcKhongChuyen)) {
			map.put(DanhSachDangKyMau1.icpcKhongChuyen, icpcKhongChuyen);
		}
		if (!ObjectUtils.isEmpty(caoDang)) {
			map.put(DanhSachDangKyMau1.caoDang, caoDang);
		}
		if (!ObjectUtils.isEmpty(procon)) {
			map.put(DanhSachDangKyMau1.procon, procon);
		}
		if (!ObjectUtils.isEmpty(pmnm)) {
			map.put(DanhSachDangKyMau1.pmnm, pmnm);
		}
		if (!ObjectUtils.isEmpty(giai)) {
			map.put(DanhSachDangKyMau1.giai, giai);
		}
		if (!ObjectUtils.isEmpty(namThamDu)) {
			map.put(DanhSachDangKyMau1.namThamDu, namThamDu);
		}

		return map;
	}

}
